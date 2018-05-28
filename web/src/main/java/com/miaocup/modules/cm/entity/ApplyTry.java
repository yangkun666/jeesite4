/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 申请试用Entity
 * @author yangkun
 * @version 2018-04-17
 */
@Table(name="t_apply_try", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="phone", attrName="phone", label="手机号"),
		@Column(name="address", attrName="address", label="公司地址"),
		@Column(name="content", attrName="content", label="详情内容"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class ApplyTry extends DataEntity<ApplyTry> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String phone;		// 手机号
	private String address;		// 公司地址
	private String content;		// 详情内容
	
	public ApplyTry() {
		this(null);
	}

	public ApplyTry(String id){
		super(id);
	}
	
	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=32, message="姓名长度不能超过 32 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="手机号不能为空")
	@Length(min=0, max=32, message="手机号长度不能超过 32 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotBlank(message="公司地址不能为空")
	@Length(min=0, max=128, message="公司地址长度不能超过 128 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank(message="详情内容不能为空")
	@Length(min=0, max=512, message="详情内容长度不能超过 512 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}