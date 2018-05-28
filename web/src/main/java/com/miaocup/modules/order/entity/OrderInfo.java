/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.entity;

import com.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 订单管理Entity
 * @author yangkun
 * @version 2018-04-16
 */
@Table(name="t_order_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="cm_id", attrName="cmId", label="咖啡机ID"),
		@Column(name="dealer", attrName="dealer", label="经销商"),
		@Column(name="amount", attrName="amount", label="订单金额"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="payment_amount", attrName="paymentAmount", label="付款总额"),
		@Column(name="pay_channel", attrName="payChannel", label="支付渠道"),
		@Column(name="pay_id", attrName="payId", label="支付流水号"),
		@Column(name="discount_id", attrName="discountId", label="优惠券编号"),
		@Column(name="success_date", attrName="successDate", label="支付成功时间"),
		@Column(name="refund_id", attrName="refundId", label="退款流水号"),
		@Column(name="refund_date", attrName="refundDate", label="退款时间"),
		@Column(name="create_date", attrName="createDate", label="交易时间"),
	}, orderBy="a.update_date DESC"
)
public class OrderInfo extends DataEntity<OrderInfo> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String cmId;		// 咖啡机ID
	private String dealer;		// 经销商
	private Double amount;		// 订单金额
	private Double paymentAmount;		// 付款总额
	private Integer payChannel;		// 支付渠道
	private String payId;		// 支付流水号
	private String discountId;		// 优惠券编号
	private Date successDate;		// 支付成功时间
	private String refundId;		// 退款流水号
	private Date refundDate;		// 退款时间
	private Date createDate;

	@ExcelField(title = "订单时间" , align = ExcelField.Align.LEFT,sort = 10)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public OrderInfo() {
		this(null);
	}

	public OrderInfo(String id){
		super(id);
	}
	
	@NotBlank(message="用户编号不能为空")
	@Length(min=0, max=64, message="用户编号长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ExcelField(title = "咖啡机编码" , align = ExcelField.Align.LEFT,sort = 10)
	@NotBlank(message="咖啡机ID不能为空")
	@Length(min=0, max=64, message="咖啡机ID长度不能超过 64 个字符")
	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	@ExcelField(title = "经销商" , align = ExcelField.Align.LEFT,sort = 10)
	@NotBlank(message="经销商不能为空")
	@Length(min=0, max=64, message="经销商长度不能超过 64 个字符")
	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	@ExcelField(title = "订单金额" , align = ExcelField.Align.LEFT,sort = 10)
	@NotNull(message="订单金额不能为空")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@ExcelField(title = "付款金额" , align = ExcelField.Align.LEFT,sort = 10)
	@NotNull(message="付款总额不能为空")
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
	@ExcelField(title = "支付流水号" , align = ExcelField.Align.LEFT,sort = 10)
	@Length(min=0, max=64, message="支付流水号长度不能超过 64 个字符")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	@ExcelField(title = "优惠卷编码" , align = ExcelField.Align.LEFT,sort = 10)
	@Length(min=0, max=64, message="优惠券编号长度不能超过 64 个字符")
	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	@ExcelField(title = "交易成功时间" , align = ExcelField.Align.LEFT,sort = 10)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}

	@Length(min=0, max=64, message="退款流水号长度不能超过 64 个字符")
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public Date getLastTime_gte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.GTE);
	}

	public void setLastTime_gte(Date lastTime) {
		sqlMap.getWhere().and("create_date", QueryType.GTE, lastTime);
	}

	public Date getLastTime_lte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.LTE);
	}

	public void setLastTime_lte(Date lastTime) {
		sqlMap.getWhere().and("create_date", QueryType.LTE, lastTime);
	}

}