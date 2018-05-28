/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import com.jeesite.common.entity.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CoffeeMakerykResult  {

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