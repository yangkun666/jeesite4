/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.mybatis.annotation.JoinTable;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

import java.util.Date;

/**
 * 咖啡机Entity
 * @author hejun
 * @version 2018-03-17
 */
@Table(name="t_coffee_map", alias="a", columns={
		@Column(name="id", attrName="id", label="咖啡ID", isPK=true),
		@Column(name="cm_id", attrName="cmId", label="咖啡机ID"),
		@Column(name="coffee_id", attrName="coffeeId", label="咖啡品种"),
		@Column(name="price", attrName="price", label="金额"),
		@Column(name="cups", attrName="cups", label="杯量"),
		@Column(name="start_time", attrName="startTime", label="开始时间"),
		@Column(name="end_time", attrName="endTime", label="结束时间"),
	}, joinTable={
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity=Coffee.class, alias="c",
				on="c.id = a.coffee_id",
				attrName="coffee", columns={@Column(includeEntity=Coffee.class)})
}, orderBy="a.id ASC"
)
public class CoffeeMap extends DataEntity<CoffeeMap> {
	
	private static final long serialVersionUID = 1L;
	private String cmId;		// 咖啡机ID 父类
	private String coffeeId;		// 咖啡品种
	private Double price;		// 金额
	private Integer cups;       //杯量
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间

	private Coffee Coffee;   //咖啡品种
	
	public CoffeeMap() {
		this(null);
	}


	public CoffeeMap(String cmId){
		this.cmId = cmId;
	}

	@NotNull(message="金额不能为空")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotBlank(message="开始时间不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotBlank(message="结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(String coffeeId) {
		this.coffeeId = coffeeId;
	}

	@NotBlank(message="咖啡机ID不能为空")
	@Length(min=0, max=64, message="咖啡机ID长度不能超过 64 个字符")
	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	public com.miaocup.modules.cm.entity.Coffee getCoffee() {
		return Coffee;
	}

	public void setCoffee(com.miaocup.modules.cm.entity.Coffee coffee) {
		Coffee = coffee;
	}

	public Integer getCups() {
		return cups;
	}

	public void setCups(Integer cups) {
		this.cups = cups;
	}
}