/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderInfoCondition {

	private static final long serialVersionUID = 1L;
	private Date lastStatisticsTime_gte;
	private Date lastStatisticsTime_lte;
	private String coffeeMakerId;
	private String dealer;		// 经销商
	private String payChannel;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public Date getLastStatisticsTime_gte() {
		return lastStatisticsTime_gte;
	}

	public void setLastStatisticsTime_gte(Date lastStatisticsTime_gte) {
		this.lastStatisticsTime_gte = lastStatisticsTime_gte;
	}

	public Date getLastStatisticsTime_lte() {
		return lastStatisticsTime_lte;
	}

	public void setLastStatisticsTime_lte(Date lastStatisticsTime_lte) {
		this.lastStatisticsTime_lte = lastStatisticsTime_lte;
	}

	public void setCoffeeMakerId(String coffeeMakerId) {
		this.coffeeMakerId = coffeeMakerId;
	}

	public String getCoffeeMakerId() {
		return coffeeMakerId;
	}
}