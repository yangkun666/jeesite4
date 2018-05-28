/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 退款申请Entity
 * @author yangkun
 * @version 2018-04-18
 */
@Table(name="t_apply_refund", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户ID"),
		@Column(name="order_id", attrName="orderId", label="订单ID"),
		@Column(name="reason", attrName="reason", label="退款原因"),
		@Column(name="images", attrName="images", label="图片"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class ApplyRefund extends DataEntity<ApplyRefund> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String orderId;		// 订单ID
	private String reason;		// 退款原因
	private String images;		// 图片
	
	public ApplyRefund() {
		this(null);
	}

	public ApplyRefund(String id){
		super(id);
	}
	
	@NotBlank(message="用户ID不能为空")
	@Length(min=0, max=32, message="用户ID长度不能超过 32 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotBlank(message="订单ID不能为空")
	@Length(min=0, max=32, message="订单ID长度不能超过 32 个字符")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=512, message="图片长度不能超过 512 个字符")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}