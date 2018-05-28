/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 平台红包详情Entity
 * @author yangkun
 * @version 2018-03-14
 */
@Table(name="t_sales_packet_details", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="packet_id", attrName="packetId", label="套餐ID", isPK=true),
		@Column(name="coffee_id", attrName="coffeeId", label="咖啡机编号"),
		@Column(name="cups", attrName="cups", label="杯数"),
	}, orderBy="a.id DESC, a.packet_id DESC"
)
public class SalesPacketDetails extends DataEntity<SalesPacketDetails> {
	
	private static final long serialVersionUID = 1L;
	private String packetId;		// 套餐ID
	private String coffeeId;		// 咖啡机编号
	private Integer cups;		// 杯数

	public SalesPacketDetails() {
		this(null);
	}

	public SalesPacketDetails(String id){
		super(id);
	}
	
	public String getPacketId() {
		return packetId;
	}

	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}
	
	@Length(min=0, max=64, message="咖啡机编号长度不能超过 64 个字符")
	public String getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(String coffeeId) {
		this.coffeeId = coffeeId;
	}
	
	public Integer getCups() {
		return cups;
	}

	public void setCups(Integer cups) {
		this.cups = cups;
	}
	
}