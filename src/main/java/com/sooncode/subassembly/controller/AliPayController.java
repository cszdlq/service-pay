package com.sooncode.subassembly.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.sooncode.soonjdbc.service.JdbcService;
import com.sooncode.subassembly.entity.OrderPayRecord;
import com.sooncode.subassembly.pay.zhifubao.tradePay.AlipayBizModel;
import com.sooncode.subassembly.pay.zhifubao.tradePay.AlipayNotify;
import com.sooncode.subassembly.pay.zhifubao.tradePay.ToAlipayQrTradePay;
import com.sooncode.subassembly.util.GenerateUUIDUtil;
import com.sooncode.subassembly.util.OrderUtil;

@RestController
@RequestMapping(value="/alipay",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AliPayController {
    
    private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);

    static final String STRIKETHROUGH = "-";

    @Value("${open.api.domain}")
    private String openDomain;
    @Value("${order.alipay.partner}")
    private String alipayPartner;
    @Value("${order.alipay.alipayAPPID}")
    private String alipayAPPID;
    @Value("${order.alipay.alipayPrivateKey}")
    private String alipayPrivateKey;
    @Value("${order.alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${order.alipay.notify_url}")
    private  String notifyURL;

    @Value("${order.alipay.partner}")
    private  String partner;

    @Autowired
    private JdbcService jdbcService;


    @RequestMapping(value="/pay",method=RequestMethod.POST)
    public Map<String, Object> aliPayMoney(@RequestBody String requestBody){

        Map<String, Object> resultMap = new LinkedHashMap<>();

        return resultMap;

    }

    @RequestMapping(value="/saveOrderPay",method=RequestMethod.POST)
    public Map<String, Object> saveOrderPayRecord(@RequestBody String requestBody){
        OrderPayRecord orderPayRecord = JSON.parseObject(requestBody, OrderPayRecord.class);
        String orderSn = OrderUtil.getOrderNumber("ORDER");
        String appId = orderPayRecord.getAppId() != null? orderPayRecord.getAppId() : "APPID";
        orderPayRecord.setOrderSn(orderSn);
        orderPayRecord.setOrderName(appId + STRIKETHROUGH + orderPayRecord.getOrderName());

        AlipayBizModel alipayBizModel=new AlipayBizModel();
        alipayBizModel.setOut_trade_no(orderSn);
        alipayBizModel.setSubject(orderPayRecord.getOrderName());
        alipayBizModel.setTotal_amount(orderPayRecord.getOrderPrice());
        AlipayTradePrecreateResponse alipayResponse = ToAlipayQrTradePay.qrPay(alipayBizModel, notifyURL,alipayPublicKey,alipayAPPID,alipayPrivateKey,openDomain);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (!alipayResponse.getCode().equals("10000")) {
            resultMap.put("responseCode", false);
            resultMap.put(alipayResponse.getCode(), alipayResponse.getSubMsg());
            return resultMap;
        }
        orderPayRecord.setUuid(GenerateUUIDUtil.createUUID());
        orderPayRecord.setPayFlag(0);
        orderPayRecord.setPayType("alipay");
        orderPayRecord.setCreateTime(new Date());
        orderPayRecord.setUpdateTime(new Date());
        jdbcService.save(orderPayRecord);
        resultMap.put("responseCode", true);
        resultMap.put("qrCode", alipayResponse.getQrCode());
        resultMap.put("outTradeNo", alipayResponse.getOutTradeNo());
        return resultMap;
    }

    @RequestMapping(value="/callbackNotice",method=RequestMethod.POST)
    public Map<String, Object> noticeOrderPay(@RequestParam Map<String, String> params){
        logger.debug("-----------阿里支付回调进入-------" + params.toString());
        //校验是否是支付宝发来请求
        Map<String, Object> resultMap = new LinkedHashMap<>();
        boolean verify= AlipayNotify.verify(params,partner);
        if (verify) {
            OrderPayRecord record = new OrderPayRecord();
            record.setOrderSn(params.get("out_trade_no"));
            OrderPayRecord orderPayRecord = jdbcService.get(record );
            if (orderPayRecord == null || orderPayRecord.getUuid() == null) {
                resultMap.put("responseCode", false);
                resultMap.put("responseMessage", "没有响应记录");
                logger.info(JSON.toJSONString(resultMap));
                return resultMap;
            }
            if (orderPayRecord.getPayFlag() == 0) {
                record.setUuid(orderPayRecord.getUuid());
                record.setPayFlag(1);
                record.setUpdateTime(new Date());
                long update = jdbcService.update(record);
                resultMap.put("responseCode", update > 0 ? true : false);
                resultMap.put("responseMessage", "更新付款成功");
                logger.info(JSON.toJSONString(resultMap));
            }
        }else{
            resultMap.put("responseCode", false);
            resultMap.put("responseMessage",params.toString());
            logger.info(JSON.toJSONString(resultMap));
            return resultMap;
        }
        return resultMap;


    }

}
