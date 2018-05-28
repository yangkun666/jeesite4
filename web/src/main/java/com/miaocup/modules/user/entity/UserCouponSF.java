/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

import java.util.Date;


public class UserCouponSF  {

	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String couponId;		// 优惠券编号
	private Double minus;		// 减多少元
	private Integer getWay;		// 获取方式
	private Double overPrice;		// 满多少元
	private Date endDate;		// 结束时间
	private String grantGrade = "985758811048587263";		// 发放等级
	private UserGrade userGrade;
	private String type;
	private Integer num;
	private String remarks;
	public Integer getNum() {
		return num;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getMinus() {
		return minus;
	}

	public void setMinus(Double minus) {
		this.minus = minus;
	}

	public Integer getGetWay() {
		return getWay;
	}

	public void setGetWay(Integer getWay) {
		this.getWay = getWay;
	}

	public Double getOverPrice() {
		return overPrice;
	}

	public void setOverPrice(Double overPrice) {
		this.overPrice = overPrice;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGrantGrade() {
		return grantGrade;
	}

	public void setGrantGrade(String grantGrade) {
		this.grantGrade = grantGrade;
	}

	public UserGrade getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(UserGrade userGrade) {
		this.userGrade = userGrade;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
}