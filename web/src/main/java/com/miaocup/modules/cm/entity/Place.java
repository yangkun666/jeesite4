/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 场地管理Entity
 * @author hejun
 * @version 2018-03-17
 */
@Table(name="t_place", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="dealer", attrName="dealer", label="经销商"),
		@Column(name="code", attrName="code", label="场地编码"),
		@Column(name="address", attrName="address", label="地址"),
		@Column(name="custodian", attrName="custodian", label="负责人"),
		@Column(name="phone", attrName="phone", label="联系电话"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Place extends DataEntity<Place> {
	
	private static final long serialVersionUID = 1L;
	private String dealer;		// 经销商
	private String code;		// 场地编码
	private String address;		// 地址
	private String custodian;		// 负责人
	private String phone;		// 联系电话
	
	public Place() {
		this(null);
	}

	public Place(String id){
		super(id);
	}
	
	@NotBlank(message="经销商不能为空")
	@Length(min=0, max=64, message="经销商长度不能超过 64 个字符")
	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	
	@NotBlank(message="场地编码不能为空")
	@Length(min=0, max=64, message="场地编码长度不能超过 64 个字符")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotBlank(message="地址不能为空")
	@Length(min=0, max=256, message="地址长度不能超过 256 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="负责人长度不能超过 64 个字符")
	public String getCustodian() {
		return custodian;
	}

	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}
	
	@Length(min=0, max=11, message="联系电话长度不能超过 11 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}