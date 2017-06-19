/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.sooncode.subassembly.pay.zhifubao.tradePay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.gson.Gson;


public class ToAlipayQrTradePay {
	/**
	 * 条码下单支付
	 * @param out_trade_no
	 * @param auth_code
	 * @author jinlong.rhj
	 * @date 2015年4月28日
	 * @version 1.0
	 * @return 
	 */
	public static  AlipayTradePrecreateResponse qrPay(AlipayBizModel alipayBizModel,String notifyURL,String publicKey,String appID,String privatekey,String openDomain) {
		 final Logger logger = LoggerFactory.getLogger(ToAlipayQrTradePay.class);
		Gson gson=new Gson();
		AlipayClient alipayClient = AlipayAPIClientFactory.getAlipayClient(publicKey,appID,privatekey,openDomain);
		// 使用SDK，构建群发请求模型
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizContent(gson.toJson(alipayBizModel));
		request.setNotifyUrl(notifyURL);
		AlipayTradePrecreateResponse response = null;
		try {
			response = alipayClient.execute(request);
			logger.info(response.getBody());
			logger.info(response.getMsg());
			// 这里只是简单的打印，请开发者根据实际情况自行进行处理
			if (null != response && response.isSuccess()) {
				if (response.getCode().equals("10000")) {
					logger.info("商户订单号："+response.getOutTradeNo());
					logger.info("二维码值："+response.getQrCode());//商户将此二维码值生成二维码，然后展示给用户，用户用支付宝手机钱包扫码完成支付
				} else {
				//打印错误码
					logger.info("错误码："+response.getSubCode());
					logger.info("错误描述："+response.getSubMsg());
				}
			}
		} catch (Exception e) {
			logger.error("AlipayApiException---> qrPay ",e);
		}
		return response;
	}
	


}
