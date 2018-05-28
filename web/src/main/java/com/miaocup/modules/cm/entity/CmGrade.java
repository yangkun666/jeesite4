/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 咖啡机等级配置Entity
 * @author yangkun
 * @version 2018-03-14
 */
@Table(name="t_cm_grade", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="咖啡机等级名称", queryType=QueryType.LIKE),
		@Column(name="goods_limit", attrName="goodsLimit", label="商品发布数"),
		@Column(name="image_limit", attrName="imageLimit", label="图片上传数"),
		@Column(name="amount_over", attrName="amountOver", label="升级金额"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.amount_over"
)
public class CmGrade extends DataEntity<CmGrade> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 咖啡机等级名称
	private Integer goodsLimit;		// 商品发布数
	private Integer imageLimit;		// 图片上传数
	private Double amountOver;		// 升级金额
	
	public CmGrade() {
		this(null);
	}

	public CmGrade(String id){
		super(id);
	}
	
	@NotBlank(message="咖啡机等级名称不能为空")
	@Length(min=0, max=64, message="咖啡机等级名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="商品发布数不能为空")
	public Integer getGoodsLimit() {
		return goodsLimit;
	}

	public void setGoodsLimit(Integer goodsLimit) {
		this.goodsLimit = goodsLimit;
	}
	
	@NotNull(message="图片上传数不能为空")
	public Integer getImageLimit() {
		return imageLimit;
	}

	public void setImageLimit(Integer imageLimit) {
		this.imageLimit = imageLimit;
	}
	
	@NotNull(message="升级金额不能为空")
	public Double getAmountOver() {
		return amountOver;
	}

	public void setAmountOver(Double amountOver) {
		this.amountOver = amountOver;
	}
	
}