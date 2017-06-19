package com.sooncode.subassembly.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**订单支付表
 * @author songwei
 *
 */
public class OrderPayRecord implements Serializable{ 

	private static final long serialVersionUID = -7441575648549919520L;
	/**  */ 
	private String uuid; 
	/** 商品uuid */
	private String commodityUuid ;
	/** app标识:用来分辨平台app */
	private String appId ;
	/** 订单名称 */
	private String orderName ;
	/** 付款设备 */
	private String deviceType ;
	/** 付款方式（alipay-支付宝，weixinpay-微信） */
	private String payType ;
	/**  */
	private Integer delFlag = 0;
	/** 付款用户code */
	private String userCode ;
	/** 商品类型（0-vip，1-诊断，2-其他产品） */
	private Integer buyType ;
	/** 订单流水编号 */
	private String orderSn ;
	/** 订单价格 */
	private String orderPrice ;
	/**  */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime ;
	/**  */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime ;
	/** 是否已付款（0-没付款，1-已付款） */
	private Integer payFlag ;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/** 商品uuid */
	public String getCommodityUuid() { 
		return commodityUuid;
	}
	/** 商品uuid */
	public void setCommodityUuid(String commodityUuid) {
		this.commodityUuid = commodityUuid;
	}

	/** app标识:用来分辨平台app */
	public String getAppId() { 
		return appId;
	}
	/** app标识:用来分辨平台app */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/** 订单名称 */
	public String getOrderName() { 
		return orderName;
	}
	/** 订单名称 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/** 付款设备 */
	public String getDeviceType() { 
		return deviceType;
	}
	/** 付款设备 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/** 付款方式（alipay-支付宝，weixinpay-微信） */
	public String getPayType() { 
		return payType;
	}
	/** 付款方式（alipay-支付宝，weixinpay-微信） */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**  */
	public Integer getDelFlag() { 
		return delFlag;
	}
	/**  */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/** 付款用户code */
	public String getUserCode() { 
		return userCode;
	}
	/** 付款用户code */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/** 商品类型（0-vip，1-诊断，2-其他产品） */
	public Integer getBuyType() { 
		return buyType;
	}
	/** 商品类型（0-vip，1-诊断，2-其他产品） */
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	/** 订单流水编号 */
	public String getOrderSn() { 
		return orderSn;
	}
	/** 订单流水编号 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	/** 订单价格 */
	public String getOrderPrice() { 
		return orderPrice;
	}
	/** 订单价格 */
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**  */
	public java.util.Date getCreateTime() { 
		return createTime;
	}
	/**  */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/** 是否已付款（0-没付款，1-已付款） */
	public Integer getPayFlag() { 
		return payFlag;
	}
	/** 是否已付款（0-没付款，1-已付款） */
	public void setPayFlag(Integer payFlag) {
		this.payFlag = payFlag;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
