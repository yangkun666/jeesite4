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
 * 协议内容维护管理Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_protocol", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="titlle", attrName="titlle", label="协议标题", queryType=QueryType.LIKE),
		@Column(name="content", attrName="content", label="协议内容"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Protocol extends DataEntity<Protocol> {
	
	private static final long serialVersionUID = 1L;
	private String titlle;		// 协议标题
	private String content;		// 协议内容
	
	public Protocol() {
		this(null);
	}

	public Protocol(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="协议标题长度不能超过 255 个字符")
	public String getTitlle() {
		return titlle;
	}

	public void setTitlle(String titlle) {
		this.titlle = titlle;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}