

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.sooncode.subassembly.pay.zhifubao.tradePay;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**签名编码-视支付宝服务窗要求*/
//    public static final String SIGN_CHARSET      = "GBK";

    /**字符编码-传递给支付宝的数据编码*/
//    public static final String CHARSET           = "UTF-8";

    /**签名类型-视支付宝服务窗要求*/
//    public static final String SIGN_TYPE         = "RSA";
    
    //2088511053262171
//    public static final String PARTNER           = "2088221720590624";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
//    public static final String APP_ID            = "2016042901348187";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
//    public static final String PRIVATE_KEY       = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANMmM0eVMae161UgeYIONalhyn99UHtefVbY+hLLSGvUru18G2ufvRdOWC0/kAOtBG7LG5lp9ub2x9FjgYXQNox1f9qsX8KSoAmzFTMT40TXyP5GIBLgbLK/Q97ht5kKZo608TH5Ww1HErfENeBKh1bQrH7hfYIIDRrpfxuqsjvvAgMBAAECgYANNnW9A/wAd6TLReX0mnkHKWRnh+ryXV5QgfFcHbZhcQSlPb/lgzBs9e0Un8aeRERjvnfyqKvXlhe45ZYkTnwrPse879pAMBkVEE1GyYT7H13fEn7DLbCoF/tR6F6mnbix6aTWpmZG6udXXe5O1KHMPtgQYPCv42AgCU0AsWk3iQJBAPUWGKy5+oFdEEcCEcGnK4R62M2uBsU53Pn4HEROeq8Azk1RpyGr8vkFycJX4gCxahFFAYK/Q0upSAKUtYwLgHMCQQDcjTtwP5da56Ng0TR/galQvwsqzPTrNHi4KT8rc8s/aYUUvWzNQ4bZPUqQlphqoZ8Naij5DPjhOBn549hEXGOVAkBfx77k4dmwbLpLxddCox4yq//GgdhLg42Ecx4446K2ec4mEV8LnbNa9pQey1Gbi7oz/Y/VlQGrtABcYdmBXY1ZAkBlhIS1eC/c4f5r1CAMCMy8pBMp0klx8icW7bVr9NK7D4D4OPDZ0plBaVrnDsp9WoNxk0nkyTnNwRsD0QJ9BOG5AkEAlBFET2N8vgarw/5gxAMBT1G5xd4nJaDM0vdvLNpjlMt/xJhJXb3Dz4Nw67HruwarN+zKZUqmw51qxqsIWQ5eZA==";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
//    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

    /**授权访问令牌的授权类型*/
//    public static final String GRANT_TYPE        = "authorization_code";
}