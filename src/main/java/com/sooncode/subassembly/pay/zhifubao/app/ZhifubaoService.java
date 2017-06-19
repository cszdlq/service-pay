package com.sooncode.subassembly.pay.zhifubao.app;
 
 
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sooncode.subassembly.pay.zhifubao.app.config.AlipayConfig;
import com.sooncode.subassembly.pay.zhifubao.app.config.AlipayOrder;
import com.sooncode.subassembly.pay.zhifubao.app.sign.RSA;
import com.sooncode.subassembly.pay.zhifubao.app.util.AlipayCore;

 
@Service
public class ZhifubaoService {
 
	 
	/**
	 * 
	 * @param type
	 * @param ao
	 * @return orderInfo
	 */
	@SuppressWarnings("deprecation")
	public static String getOrderInfo(AlipayConfig alipayConfig ,AlipayOrder ao){
		  
		  Map<String, String> params = new HashMap<>();
		  
		  params.put("service","\"mobile.securitypay.pay\"");
		  params.put("partner","\""+alipayConfig.getPartner()+"\"");
		  params.put("_input_charset","\"UTF-8\"");
		  
		 
		  params.put("notify_url","\""+alipayConfig.getNotify_url()+"\"");
		  params.put("out_trade_no","\""+ao.getOut_trade_no()+"\"");
		  params.put("subject","\""+ao.getSubject()+"\"");
		  
		  params.put("payment_type","\"1\"");
		  params.put("seller_id","\""+alipayConfig.getSeller_id()+"\"");
		  
		  params.put("total_fee","\""+ao.getTotal_fee()+"\"");
		  
		  params.put("body","\""+ao.getBody()+"\"");
		 
		  String string = AlipayCore.createLinkString(params);
		  String sign = RSA.sign(string, alipayConfig.getRsa(), "UTF-8");
		  
		 
          
		  params= new HashMap<>();
		  params.put("service","\\\"mobile.securitypay.pay\\\"");
		  params.put("partner","\\\""+alipayConfig.getPartner()+"\\\"");
		  params.put("_input_charset","\\\"UTF-8\\\"");
		  params.put("notify_url","\\\""+alipayConfig.getNotify_url()+"\\\"");
		  params.put("out_trade_no","\\\""+ao.getOut_trade_no()+"\\\"");
		  params.put("subject","\\\""+ao.getSubject()+"\\\"");
		  params.put("payment_type","\\\"1\\\"");
		  params.put("seller_id","\\\""+alipayConfig.getSeller_id()+"\\\"");
		 
		  params.put("total_fee","\\\""+ao.getTotal_fee()+"\\\"");
		  params.put("body","\\\""+ao.getBody()+"\\\"");
		  
		  params.put("sign_type","\\\"RSA\\\"");
		  sign = URLEncoder.encode(sign);
		  params.put("sign","\\\""+sign+"\\\"");
		   
		  String orderInfo = AlipayCore.createLinkString(params);
		    
		  return orderInfo;
	}
	
	
	
	
	
	public static void main(String[] args) {
		AlipayOrder ao = new AlipayOrder();
		ao.setBody("测试测试");
		ao.setOut_trade_no("0819145412-6177");
		ao.setSubject("测试");
		ao.setTotal_fee("0.01");
		
		//String str = getOrderInfo("CHU_ZHONG",ao);
		
		//System.out.println("ZhifubaoService.main()  :\r\n"+str);
		
	}
	
	 
}
