/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 用户优惠券Entity
 * @author yangkun
 * @version 2018-04-01
 */
@Table(name="t_user_coupon", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="coupon_id", attrName="couponId", label="优惠券编号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class UserCoupon extends DataEntity<UserCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String couponId;		// 优惠券编号
	
	public UserCoupon() {
		this(null);
	}

	public UserCoupon(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="用户编号长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="优惠券编号长度不能超过 255 个字符")
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
}