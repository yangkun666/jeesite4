/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.Sales;
import com.miaocup.modules.sales.dao.SalesDao;

/**
 * 限时促销配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesService extends CrudService<SalesDao, Sales> {
	
	/**
	 * 获取单条数据
	 * @param sales
	 * @return
	 */
	@Override
	public Sales get(Sales sales) {
		return super.get(sales);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param sales
	 * @return
	 */
	@Override
	public Page<Sales> findPage(Page<Sales> page, Sales sales) {
		return super.findPage(page, sales);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sales
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Sales sales) {
		super.save(sales);
	}
	
	/**
	 * 更新状态
	 * @param sales
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Sales sales) {
		super.updateStatus(sales);
	}
	
	/**
	 * 删除数据
	 * @param sales
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Sales sales) {
		super.delete(sales);
	}
	
}