/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 促销活动授权配置Entity
 * @author yangkun
 * @version 2018-03-31
 */
@Table(name="t_sales_auth", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="activity_id", attrName="activityId", label="活动编号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class SalesAuth extends DataEntity<SalesAuth> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动编号
	
	public SalesAuth() {
		this(null);
	}

	public SalesAuth(String id){
		super(id);
	}
	
	@NotBlank(message="活动编号不能为空")
	@Length(min=0, max=64, message="活动编号长度不能超过 64 个字符")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
}