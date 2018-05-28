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
 * 用户余额明细Entity
 * @author yangkun
 * @version 2018-03-30
 */
@Table(name="t_user_money", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="type", attrName="type", label="类型"),
		@Column(name="money", attrName="money", label="金钱"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class UserMoney extends DataEntity<UserMoney> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String type;		// 类型
	private Integer money;		// 金钱
	
	public UserMoney() {
		this(null);
	}

	public UserMoney(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="用户编号长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="类型长度不能超过 255 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
}