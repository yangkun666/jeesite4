/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 公告维护管理Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_announcement", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="content", attrName="content", label="公告内容"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class Announcement extends DataEntity<Announcement> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 公告内容
	private String title;		// 标题
	
	public Announcement() {
		this(null);
	}

	public Announcement(String id){
		super(id);
	}
	
	@NotBlank(message="公告内容不能为空")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="标题长度不能超过 255 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}