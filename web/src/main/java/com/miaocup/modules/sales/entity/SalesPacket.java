/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 平台红包配置Entity
 * @author yangkun
 * @version 2018-03-14
 */
@Table(name="t_sales_packet", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_grade", attrName="userGrade", label="用户等级"),
		@Column(name="start_date", attrName="startDate", label="开始日期"),
		@Column(name="end_date", attrName="endDate", label="结束日期"),
		@Column(name="exchange_way", attrName="exchangeWay", label="兑换方式"),
		@Column(name="count", attrName="count", label="发放数量"),
		@Column(name="amount", attrName="amount", label="发放金额"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class SalesPacket extends DataEntity<SalesPacket> {
	
	private static final long serialVersionUID = 1L;
	private String userGrade;		// 用户等级
	private Date startDate;		// 开始日期
	private Date endDate;		// 结束日期
	private Integer exchangeWay;		// 兑换方式
	private Integer count;		// 发放数量
	private Double amount;		// 发放金额
	
	public SalesPacket() {
		this(null);
	}

	public SalesPacket(String id){
		super(id);
	}
	
	@NotBlank(message="用户等级不能为空")
	@Length(min=0, max=255, message="用户等级长度不能超过 255 个字符")
	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始日期不能为空")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结束日期不能为空")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@NotNull(message="兑换方式不能为空")
	public Integer getExchangeWay() {
		return exchangeWay;
	}

	public void setExchangeWay(Integer exchangeWay) {
		this.exchangeWay = exchangeWay;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}