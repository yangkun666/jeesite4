/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 咖啡机保证金Entity
 * @author hejun
 * @version 2018-03-18
 */
@Table(name="t_cash_deposit", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="cm_type", attrName="cmType", label="cm_type"),
		@Column(name="cash_deposit", attrName="cashDeposit", label="cash_deposit"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class CashDeposit extends DataEntity<CashDeposit> {
	
	private static final long serialVersionUID = 1L;
	private Integer cmType;		// cm_type
	private Double cashDeposit;		// cash_deposit
	
	public CashDeposit() {
		this(null);
	}

	public CashDeposit(String id){
		super(id);
	}
	
	@NotNull(message="cm_type不能为空")
	public Integer getCmType() {
		return cmType;
	}

	public void setCmType(Integer cmType) {
		this.cmType = cmType;
	}
	
	@NotNull(message="cash_deposit不能为空")
	public Double getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(Double cashDeposit) {
		this.cashDeposit = cashDeposit;
	}
	
}