/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 投诉建议Entity
 * @author yangkun
 * @version 2018-04-11
 */
@Table(name="t_opinion", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="content", attrName="content", label="内容"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Opinion extends DataEntity<Opinion> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String content;		// 内容
	
	public Opinion() {
		this(null);
	}

	public Opinion(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="用户编号长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="内容长度不能超过 255 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}