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
 * 会员等级配置Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_user_grade", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="name", attrName="name", label="名称", queryType=QueryType.LIKE),
		@Column(name="begin_number", attrName="beginNumber", label="开始数值"),
		@Column(name="end_number", attrName="endNumber", label="结束数值"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class UserGrade extends DataEntity<UserGrade> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Long beginNumber;		// 开始数值
	private Long endNumber;		// 结束数值
	
	public UserGrade() {
		this(null);
	}

	public UserGrade(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Long beginNumber) {
		this.beginNumber = beginNumber;
	}
	
	public Long getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(Long endNumber) {
		this.endNumber = endNumber;
	}
	
}