package com.sooncode.subassembly.pay.zhifubao.app.config;

/**
 * 支付宝支付订单模型
 * 
 * @author pc
 *
 */
public class AlipayOrder {

	/** 内部订单号 */
	private String out_trade_no;

	/** 商品名称 */
	private String subject;

	/** 总金额 */
	private String total_fee;

	/** 商品描述 */
	private String body;

	/** 内部订单号 */
	public String getOut_trade_no() {
		return out_trade_no;
	}

	/** 内部订单号 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/** 商品名称 */
	public String getSubject() {
		return subject;
	}

	/** 商品名称 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** 总金额 */
	public String getTotal_fee() {
		return total_fee;
	}

	/** 总金额 */
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	/** 商品描述 */
	public String getBody() {
		return body;
	}

	/** 商品描述 */
	public void setBody(String body) {
		this.body = body;
	}

}
