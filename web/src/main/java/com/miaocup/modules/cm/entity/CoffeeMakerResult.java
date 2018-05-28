/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.miaocup.modules.dealer.entity.DealerDto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
public class CoffeeMakerResult extends DataEntity<CoffeeMakerResult> {
	private Date lastStatisticsTime_gte;
	private Date lastStatisticsTime_lte;
	private String cmId;
	private String code;
	private String dealer;		// 经销商
	private String address;
	private String count;		// 经销商
	private String sum;
	private String ave;
}