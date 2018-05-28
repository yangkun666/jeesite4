/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.coupon.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.miaocup.modules.user.entity.UserGrade;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 优惠券Entity
 * @author yangkun
 * @version 2018-04-13
 */
@Table(name="t_coupon", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="minus", attrName="minus", label="减多少元"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="get_way", attrName="getWay", label="获取方式"),
		@Column(name="over_price", attrName="overPrice", label="满多少元"),
		@Column(name="end_date", attrName="endDate", label="结束时间"),
		@Column(name="grant_grade", attrName="grantGrade", label="发放等级"),
		@Column(name="type", attrName="type", label="优惠卷等级"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=UserGrade.class, alias="c",
				on="c.id = a.grant_grade",
				attrName="userGrade", columns={@Column(includeEntity=UserGrade.class)})
	}, orderBy="a.update_date DESC"
)
public class Coupon extends DataEntity<Coupon> {
	
	private static final long serialVersionUID = 1L;
	private Double minus;		// 减多少元
	private Integer getWay;		// 获取方式
	private Double overPrice;		// 满多少元
	private Date endDate;		// 结束时间
	private String grantGrade;		// 发放等级
	private UserGrade userGrade;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserGrade getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(UserGrade userGrade) {
		this.userGrade = userGrade;
	}

	public Coupon() {
		this(null);
	}

	public Coupon(String id){
		super(id);
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Length(min=0, max=50, message="发放等级长度不能超过 50 个字符")
	public String getGrantGrade() {
		return grantGrade;
	}

	public void setGrantGrade(String grantGrade) {
		this.grantGrade = grantGrade;
	}
	
}