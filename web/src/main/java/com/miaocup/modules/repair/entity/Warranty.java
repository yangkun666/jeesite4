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
 * 保修有礼Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_warranty", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="cm_model", attrName="cmModel", label="咖啡机类型"),
		@Column(name="dealer", attrName="dealer", label="经销商"),
		@Column(name="customer_id", attrName="customerId", label="客户"),
		@Column(name="reason", attrName="reason", label="保修原因"),
		@Column(name="is_handle", attrName="isHandle", label="是否处理"),
		@Column(name="is_giv", attrName="isGiv", label="是否送礼"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Warranty extends DataEntity<Warranty> {
	
	private static final long serialVersionUID = 1L;
	private String cmModel;		// 咖啡机类型
	private String dealer;		// 经销商
	private String customerId;		// 客户
	private String reason;		// 保修原因
	private String isHandle;		// 是否处理
	private String isGiv;		// 是否送礼
	
	public Warranty() {
		this(null);
	}

	public Warranty(String id){
		super(id);
	}
	
	@NotBlank(message="咖啡机类型不能为空")
	@Length(min=0, max=255, message="咖啡机类型长度不能超过 255 个字符")
	public String getCmModel() {
		return cmModel;
	}

	public void setCmModel(String cmModel) {
		this.cmModel = cmModel;
	}
	
	@Length(min=0, max=255, message="经销商长度不能超过 255 个字符")
	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	
	@Length(min=0, max=255, message="客户长度不能超过 255 个字符")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="是否处理长度不能超过 255 个字符")
	public String getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}
	
	@Length(min=0, max=255, message="是否送礼长度不能超过 255 个字符")
	public String getIsGiv() {
		return isGiv;
	}

	public void setIsGiv(String isGiv) {
		this.isGiv = isGiv;
	}
	
}