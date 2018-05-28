/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 短信群发设置Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_msg", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="content", attrName="content", label="短信内容"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Msg extends DataEntity<Msg> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 短信内容
	
	public Msg() {
		this(null);
	}

	public Msg(String id){
		super(id);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}