/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 咖啡品种Entity
 * @author hejun
 * @version 2018-03-17
 */
@Table(name="t_coffee", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="咖啡名称", queryType=QueryType.LIKE),
		@Column(name="images", attrName="images", label="图片"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Coffee extends DataEntity<Coffee> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 咖啡名称
	private String images;		// 图片
	private String describe;		// 描述
	
	public Coffee() {
		this(null);
	}

	public Coffee(String id){
		super(id);
	}
	
	@NotBlank(message="咖啡名称不能为空")
	@Length(min=0, max=64, message="咖啡名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="图片不能为空")
	@Length(min=0, max=2000, message="图片长度不能超过 2000 个字符")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	@Length(min=0, max=512, message="描述长度不能超过 512 个字符")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}