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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sooncode.soonjdbc.service.JdbcService;
import com.sooncode.subassembly.entity.WeixinPayRecord;
import com.sooncode.subassembly.pay.weixin.ParaXml;
import com.sooncode.subassembly.pay.weixin.WeixinService;
import com.sooncode.subassembly.pay.weixin.WeixinUtil;
import com.sooncode.subassembly.pay.weixin.module.Orderquery;
import com.sooncode.subassembly.pay.weixin.module.UnifiedOrder;
import com.sooncode.subassembly.pay.weixin.weixinPay.NonRetOrder;
import com.sooncode.subassembly.pay.weixin.weixinPay.WeixinNewService;
import com.sooncode.subassembly.pay.weixin.weixinPay.queryWinxinReturnOrder;
import com.sooncode.subassembly.util.GenerateUUIDUtil;
import com.sooncode.subassembly.util.OrderUtil;

@RestController
@RequestMapping(value = "/weixinpay")
public class WeixinPayController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinPayController.class);
    static final String STRIKETHROUGH = "-";

    @Value("${order.weixin.appid}")
    private String normalAppId;
    @Value("${order.weixin.mch_id}")
    private String normalMchId;
    @Value("${order.weixin.trade_type}")
    private String tradeType;
    @Value("${order.weixin.notyify_url}")
    private String notyifyUrl;
    @Value("${order.weixin.spbill_create_ip}")
    private String spbillIp;
    @Value("${order.weixin.partnerKey}")
    private String key;

    @Autowired
    private JdbcService jdbcService;

    @RequestMapping(value = "/saveWxOrderPay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> saveWxOrderPayRecord(@RequestBody String requestBody) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {
            WeixinPayRecord orderPayRecord = JSON.parseObject(requestBody, WeixinPayRecord.class);
            String appId = orderPayRecord.getAppid() != null ? orderPayRecord.getAppid() : "APPID";
            String outTradeNo = OrderUtil.getOrderNumber("ORDER");
            Long totalFee = orderPayRecord.getTotalFee();
            String orderName = orderPayRecord.getOrderName();
            //微信支付请求体
            UnifiedOrder uo = new UnifiedOrder();
            uo.setAppid(normalAppId);
            uo.setMch_id(normalMchId);
            uo.setKey(key);
            uo.setBody(orderName);
            uo.setOut_trade_no(outTradeNo);
            uo.setNonce_str(WeixinUtil.getUUID());
            uo.setTotal_fee(totalFee + "");
            uo.setNotify_url(notyifyUrl);
            uo.setSpbill_create_ip(spbillIp);
            uo.setTrade_type(tradeType);
            NonRetOrder retOrder = WeixinService.unifiedorder4NATIVE(uo);
            if (retOrder.getResult_code()==null || !retOrder.getResult_code().equals("SUCCESS") || !retOrder.getReturn_code().equals("SUCCESS")) {
                resultMap.put("responseCode", false);
                resultMap.put("responseMessage", JSON.toJSON(retOrder));
                return resultMap;
            }
            orderPayRecord.setUuid(GenerateUUIDUtil.createUUID());
            orderPayRecord.setOutTradeNo(outTradeNo);
            orderPayRecord.setOrderName(appId + STRIKETHROUGH + orderName);
            orderPayRecord.setCreateTime(new Date());
            orderPayRecord.setUpdateTime(new Date());
            long save = jdbcService.save(orderPayRecord);
            resultMap.put("responseCode", save > 0 ? true : false);
            resultMap.put("codeUrl", retOrder.getCode_url());
            resultMap.put("outTradeNo", retOrder.getPrepay_id());
        } catch (Exception e) {
            logger.error("saveOrderPayRecord() err",e);
            resultMap.put("responseCode", false);
            resultMap.put("responseMessage", "saveOrderPayRecord() err");
            return resultMap;
        }
        return resultMap;
    }


    @RequestMapping(value="/callbackNotice",method=RequestMethod.POST)
    public String noticeOrderPay(@RequestBody String requestBody){
        logger.debug("-----------微信支付回调进入-------" + requestBody);

        ParaXml px = new ParaXml(requestBody);
        StringBuffer xml = new StringBuffer();
        if (px.getValue("return_code").equals("SUCCESS") && px.getValue("result_code").equals("SUCCESS")) {
            WeixinPayRecord wpr = new WeixinPayRecord();
            wpr.setOutTradeNo(px.getValue("out_trade_no"));
            WeixinPayRecord record = jdbcService.get(wpr);
            if (record != null && record.getUuid() != null) {
                if (record.getTotalFee().toString().equals(px.getValue("total_fee"))) {
                    record.setTradeState("SUCESS");
                    record.setUpdateTime(new Date());
                    jdbcService.update(record);
                    xml.append("<xml>");
                    xml.append("<return_code>" + "SUCCESS" + "</return_code>");
                    xml.append("<return_msg>" + "OK" + "</return_msg>");
                    xml.append("</xml>");
                }else {
                    logger.error("订单金额不对！");
                }
            }else {
                logger.error("没有此订单！");
                xml.append("<xml>");
                xml.append("<return_code>" + "SUCCESS" + "</return_code>");
                xml.append("<return_msg>" + "订单丢失" + "</return_msg>");
                xml.append("</xml>");
            }
        }

        return xml.toString();
    }


    @RequestMapping(value = "/queryWxOrderPayRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryWxOrderPayRecord(@RequestBody String requestBody) {
        WeixinPayRecord orderPayRecord = JSON.parseObject(requestBody, WeixinPayRecord.class);
        String outTradeNo = orderPayRecord.getOutTradeNo();
        WeixinPayRecord record = jdbcService.get(orderPayRecord);
        String uuid = record.getUuid();
        //查询订单
        Orderquery o = new Orderquery();
        o.setAppid(normalAppId);
        o.setKey(key);
        o.setMch_id(normalMchId);
        o.setNonce_str(WeixinUtil.getUUID());
        o.setOut_trade_no(outTradeNo);
        queryWinxinReturnOrder res = WeixinNewService.orderquery(o);

        //更新数据表
        WeixinPayRecord newRecord = new WeixinPayRecord();
        newRecord.setUuid(uuid);
        newRecord.setBankType(res.getBank_type());
        newRecord.setFeeType(res.getFee_type());
        newRecord.setOpenid(res.getOpenid());
        newRecord.setTradeState(res.getTrade_state());
        newRecord.setTradeType(res.getTrade_type());
        newRecord.setTransactionId(res.getTransaction_id());
        newRecord.setUpdateTime(new Date());
        long r = jdbcService.saveOrUpdate(newRecord);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("returnCode", r > 0 ? true : false);
        resultMap.put("outTradeNo", outTradeNo);
        return resultMap;
    }


}
