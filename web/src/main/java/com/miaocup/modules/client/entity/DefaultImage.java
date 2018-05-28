/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.client.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 默认图片配置Entity
 * @author yangkun
 * @version 2018-03-14
 */
@Table(name="t_default_image", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="默认图片名称", queryType=QueryType.LIKE),
		@Column(name="image", attrName="image", label="图片地址"),
	}, orderBy="a.id DESC"
)
public class DefaultImage extends DataEntity<DefaultImage> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 默认图片名称
	private String image;		// 图片地址
	
	public DefaultImage() {
		this(null);
	}

	public DefaultImage(String id){
		super(id);
	}
	
	@NotBlank(message="默认图片名称不能为空")
	@Length(min=0, max=64, message="默认图片名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="图片地址长度不能超过 256 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}