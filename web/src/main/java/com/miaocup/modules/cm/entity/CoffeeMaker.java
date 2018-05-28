/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;

import java.sql.Time;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 咖啡机Entity
 * @author hejun
 * @version 2018-03-17
 */
@Table(name="t_coffee_maker", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="machine_id", attrName="machineId", label="咖啡机ID"),
		@Column(name="code", attrName="code", label="咖啡机编码"),
		@Column(name="place_id", attrName="placeId", label="场地ID"),
		@Column(name="model", attrName="model", label="咖啡机型号"),
		@Column(name="grade", attrName="grade", label="咖啡机等级"),
		@Column(name="type", attrName="type", label="咖啡机类别"),
		@Column(name="cup_limit", attrName="cupLimit", label="咖啡机杯量"),
		@Column(name="startup_time", attrName="startupTime", label="每日开机时间"),
		@Column(name="shutdown_time", attrName="shutdownTime", label="每日关机时间"),
		@Column(name="qr_code", attrName="qrCode", label="二维码"),
		@Column(name="default_price", attrName="defaultPrice", label="默认金额"),
		@Column(name="longitude", attrName="longitude", label="默认金额"),
		@Column(name="latitude", attrName="latitude", label="默认金额"),
		@Column(name="dealer", attrName="dealer", label="经销商"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="oper_status", attrName="operStatus", label="经营状态"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=Place.class, alias="p",
				on="p.id = a.place_id",
				attrName="place", columns={@Column(includeEntity=Place.class)}),
		@JoinTable(type=Type.LEFT_JOIN, entity=CmGrade.class, alias="g",
				on="g.id = a.grade",
				attrName="cmGrade", columns={@Column(includeEntity=CmGrade.class)})
	},orderBy="a.update_date DESC"
)
public class CoffeeMaker extends DataEntity<CoffeeMaker> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 咖啡机ID
	private String code;		// 咖啡机编码
	private String placeId;		// 场地ID
	private String model;		// 咖啡机型号
	private String grade;		// 咖啡机等级
	private Integer type;		// 咖啡机类别
	private Integer cupLimit;		// 咖啡机杯量
	private Time startupTime;		// 每日开机时间
	private Time shutdownTime;		// 每日关机时间
	private String qrCode;		// 二维码
	private Double defaultPrice;		// 默认金额
	private Integer operStatus;		// 经营状态
	private List<CoffeeMap> coffeeMapList = ListUtils.newArrayList();		// 子表列表
	private String dealer;
	private String latitude; //经
	private String longitude; // 纬
	private Integer runStatus;		// 运行状态
	private Place place;   //场地
	private CmGrade cmGrade;  //等级
	
	public CoffeeMaker() {
		this(null);
	}

	public CoffeeMaker(String id){
		this.id = id;
	}


	@NotBlank(message="编码不能为空")
	@Length(min=0, max=64, message="编码长度不能超过 64 个字符")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank(message="场地不能为空")
	@Length(min=0, max=64, message="场地长度不能超过 64 个字符")
	public String getPlaceId() {
		return placeId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	@NotBlank(message="型号不能为空")
	@Length(min=0, max=64, message="型号长度不能超过 64 个字符")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@NotBlank(message="等级不能为空")
	@Length(min=0, max=64, message="等级长度不能超过 64 个字符")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(min=0, max=64, message="等级长度不能超过 64 个字符")
	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	@NotNull(message="类别不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@NotNull(message="杯量不能为空")
	public Integer getCupLimit() {
		return cupLimit;
	}

	public void setCupLimit(Integer cupLimit) {
		this.cupLimit = cupLimit;
	}
	
	@NotNull(message="开机时间不能为空")
	public Time getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(String startupTime) {
		if (startupTime != null) {
			String[] split = startupTime.split(":");
			this.startupTime = new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);
		}
	}
	
	@NotNull(message="关机时间不能为空")
	public Time getShutdownTime() {
		return shutdownTime;
	}

	public void setShutdownTime(String shutdownTime) {
		if (shutdownTime != null) {
			String[] split = shutdownTime.split(":");
			this.shutdownTime = new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);
		}
	}
	
	@NotBlank(message="二维码不能为空")
	@Length(min=0, max=128, message="二维码长度不能超过 128 个字符")
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	@NotNull(message="默认金额不能为空")
	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	
	public Integer getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}
	
	public Integer getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(Integer operStatus) {
		this.operStatus = operStatus;
	}
	
	public List<CoffeeMap> getCoffeeMapList() {
		return coffeeMapList;
	}

	public void setCoffeeMapList(List<CoffeeMap> coffeeMapList) {
		this.coffeeMapList = coffeeMapList;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public CmGrade getCmGrade() {
		return cmGrade;
	}

	public void setCmGrade(CmGrade cmGrade) {
		this.cmGrade = cmGrade;
	}

	@NotBlank(message="咖啡机ID不能为空")
	@Length(min=0, max=64, message="咖啡机ID长度不能超过 64 个字符")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
}