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
 * 会员经验配置Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_user_experience", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="numerical", attrName="numerical", label="经验值"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="type", attrName="type", label="用户操作"),
	}, orderBy="a.update_date DESC"
)
public class UserExperience extends DataEntity<UserExperience> {
	
	private static final long serialVersionUID = 1L;
	private Long numerical;		// 经验值
	private String type;		// 用户操作
	
	public UserExperience() {
		this(null);
	}

	public UserExperience(String id){
		super(id);
	}
	
	public Long getNumerical() {
		return numerical;
	}

	public void setNumerical(Long numerical) {
		this.numerical = numerical;
	}
	
	@Length(min=0, max=255, message="用户操作长度不能超过 255 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}