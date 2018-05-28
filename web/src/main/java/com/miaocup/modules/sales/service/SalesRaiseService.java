/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesRaise;
import com.miaocup.modules.sales.dao.SalesRaiseDao;

/**
 * 加价购管理Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesRaiseService extends CrudService<SalesRaiseDao, SalesRaise> {
	
	/**
	 * 获取单条数据
	 * @param salesRaise
	 * @return
	 */
	@Override
	public SalesRaise get(SalesRaise salesRaise) {
		return super.get(salesRaise);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesRaise
	 * @return
	 */
	@Override
	public Page<SalesRaise> findPage(Page<SalesRaise> page, SalesRaise salesRaise) {
		return super.findPage(page, salesRaise);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesRaise
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesRaise salesRaise) {
		super.save(salesRaise);
	}
	
	/**
	 * 更新状态
	 * @param salesRaise
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesRaise salesRaise) {
		super.updateStatus(salesRaise);
	}
	
	/**
	 * 删除数据
	 * @param salesRaise
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesRaise salesRaise) {
		super.delete(salesRaise);
	}
	
}