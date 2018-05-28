/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

import java.util.Date;

/**
 * 用户流水Entity
 * @author hejun
 * @version 2018-03-14
 */
@Table(name="t_user_statement", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户ID"),
		@Column(name="order_id", attrName="orderId", label="订单ID"),
		@Column(name="type", attrName="type", label="类型"),
		@Column(name="deal_date", attrName="dealDate", label="时间"),
	}, orderBy="a.id DESC"
)
public class UserStatement extends DataEntity<UserStatement> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String orderId;		// 订单ID
	private Integer type;		// 类型
	private Date dealDate;      //时间
	
	public UserStatement() {
		this(null);
	}

	public UserStatement(String id){
		super(id);
	}
	
	@NotBlank(message="用户ID不能为空")
	@Length(min=0, max=64, message="用户ID长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotBlank(message="订单ID不能为空")
	@Length(min=0, max=64, message="订单ID长度不能超过 64 个字符")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@NotNull(message="类型不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreateDate_gte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.GTE);
	}

	public void setCreateDate_gte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.GTE, createDate);
	}
	
	public Date getCreateDate_lte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.LTE);
	}

	public void setCreateDate_lte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.LTE, createDate);
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
}