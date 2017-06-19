package com.sooncode.subassembly.entity;

import java.util.Date;

public class WeixinPayRecord {
	
	private String uuid;
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 标价金额
	 */
	private Long totalFee;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 付款银行
	 */
	private String bankType;
	/**
	 * 标价币种
	 */
	private String feeType;
	/**
	 * 微信订单号
	 */
	private String transactionId;
	/**
	 * 交易状态
	 */
	private String tradeState;
	
	private Date createTime;
	
	private Date updateTime;

	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTransactionId() {
		return transactionId;
	}
	
	

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}	
