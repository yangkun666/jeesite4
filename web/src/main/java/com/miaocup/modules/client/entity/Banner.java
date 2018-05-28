/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.client.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 广告管理Entity
 * @author yangkun
 * @version 2018-03-14
 */
@Table(name="t_banner", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="名称", queryType=QueryType.LIKE),
		@Column(name="position", attrName="position", label="位置"),
		@Column(name="start_date", attrName="startDate", label="开始时间", queryType=QueryType.LT),
		@Column(name="end_date", attrName="endDate", label="结束时间", queryType=QueryType.GT),
		@Column(name="image", attrName="image", label="图片"),
		@Column(name="url", attrName="url", label="链接地址"),
		@Column(name="sort", attrName="sort", label="序号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Banner extends DataEntity<Banner> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer position;		// 位置
	private Date startDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String image;		// 图片
	private String url;		// 链接地址
	private Integer sort;		// 序号
	
	public Banner() {
		this(null);
	}

	public Banner(String id){
		super(id);
	}
	
	@NotBlank(message="名称不能为空")
	@Length(min=0, max=128, message="名称长度不能超过 128 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="位置不能为空")
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始时间不能为空")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结束时间不能为空")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@NotBlank(message="图片不能为空")
	@Length(min=0, max=256, message="图片长度不能超过 256 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=256, message="链接地址长度不能超过 256 个字符")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@NotNull(message="序号不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}