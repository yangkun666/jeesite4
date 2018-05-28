/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.coupon.entity;

import com.jeesite.common.entity.DataEntity;

/**
 * 优惠券Entity
 * @author yangkun
 * @version 2018-04-13
 */

public class UserCouponDate extends DataEntity<UserCouponDate> {

	private static final long serialVersionUID = 1L;
	private String couponId;		// 优惠卷
	private String number;		// 数目
	private String userId;
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}