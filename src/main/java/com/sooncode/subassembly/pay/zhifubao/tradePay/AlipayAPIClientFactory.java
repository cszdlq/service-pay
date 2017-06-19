/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.sooncode.subassembly.pay.zhifubao.tradePay;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;


/**
 * API调用客户端工厂
 * 
 */
public class AlipayAPIClientFactory {

    /** API调用客户端 */
    private static AlipayClient alipayClient;
    
    /**
     * 获得API调用客户端
     * 
     * @return
     */
    public static AlipayClient getAlipayClient(String ALIPAY_PUBLIC_KEY,String APP_ID,String PRIVATE_KEY,String OPEN_API_DOMAIN){
        
        if(null == alipayClient){
            alipayClient = new DefaultAlipayClient(OPEN_API_DOMAIN, APP_ID, PRIVATE_KEY, AlipayConstants.FORMAT_JSON, 
                AlipayConstants.CHARSET_UTF8,ALIPAY_PUBLIC_KEY);
        }
        return alipayClient;
    }
}
