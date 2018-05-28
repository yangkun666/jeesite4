/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesPackage;
import com.miaocup.modules.sales.dao.SalesPackageDao;

/**
 * 优惠套餐配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesPackageService extends CrudService<SalesPackageDao, SalesPackage> {
	
	/**
	 * 获取单条数据
	 * @param salesPackage
	 * @return
	 */
	@Override
	public SalesPackage get(SalesPackage salesPackage) {
		return super.get(salesPackage);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesPackage
	 * @return
	 */
	@Override
	public Page<SalesPackage> findPage(Page<SalesPackage> page, SalesPackage salesPackage) {
		return super.findPage(page, salesPackage);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesPackage
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesPackage salesPackage) {
		super.save(salesPackage);
	}
	
	/**
	 * 更新状态
	 * @param salesPackage
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesPackage salesPackage) {
		super.updateStatus(salesPackage);
	}
	
	/**
	 * 删除数据
	 * @param salesPackage
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesPackage salesPackage) {
		super.delete(salesPackage);
	}
	
}