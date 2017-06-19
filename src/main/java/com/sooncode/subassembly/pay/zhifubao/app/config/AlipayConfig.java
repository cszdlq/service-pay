package com.sooncode.subassembly.pay.zhifubao.app.config;


/**
 * 支付宝移动支付 有关账号的参数模型
 * 
 * @author pc
 *
 */
public class AlipayConfig {

	/** 合作身份者ID */
	private String partner;

	/** 商户的私钥 */
	private String private_key;

	/** 支付宝的公钥 */
	private String ali_public_key;

	/** 字符编码格式 */
	private String input_charset = "utf-8";

	/** 签名方式 */
	private String sign_type = "RSA";

	/** 服务器异步通知回掉地址（接口） */
	private String notify_url;

	/** 客户端号 */
	private String app_id;
	
	
	private String rsa;
	
	private String seller_id;

	/** 合作身份者ID */
	public String getPartner() {
		return partner;
	}

	/** 合作身份者ID */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	/** 商户的私钥 */
	public String getPrivate_key() {
		return private_key;
	}

	/** 商户的私钥 */
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	/** 支付宝的公钥 */
	public String getAli_public_key() {
		return ali_public_key;
	}

	/** 支付宝的公钥 */
	public void setAli_public_key(String ali_public_key) {
		this.ali_public_key = ali_public_key;
	}

	/** 字符编码格式 */
	public String getInput_charset() {
		return input_charset;
	}

	/** 字符编码格式 */
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	/** 签名方式 */
	public String getSign_type() {
		return sign_type;
	}

	/** 签名方式 */
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	/** 服务器异步通知回掉地址（接口） */
	public String getNotify_url() {
		return notify_url;
	}

	/** 服务器异步通知回掉地址（接口） */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	/** 客户端号 */
	public String getApp_id() {
		return app_id;
	}

	/** 客户端号 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getRsa() {
		return rsa;
	}

	public void setRsa(String rsa) {
		this.rsa = rsa;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	
	
}
