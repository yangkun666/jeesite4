/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesPresent;
import com.miaocup.modules.sales.dao.SalesPresentDao;

/**
 * 满即送配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesPresentService extends CrudService<SalesPresentDao, SalesPresent> {
	
	/**
	 * 获取单条数据
	 * @param salesPresent
	 * @return
	 */
	@Override
	public SalesPresent get(SalesPresent salesPresent) {
		return super.get(salesPresent);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesPresent
	 * @return
	 */
	@Override
	public Page<SalesPresent> findPage(Page<SalesPresent> page, SalesPresent salesPresent) {
		return super.findPage(page, salesPresent);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesPresent
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesPresent salesPresent) {
		super.save(salesPresent);
	}
	
	/**
	 * 更新状态
	 * @param salesPresent
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesPresent salesPresent) {
		super.updateStatus(salesPresent);
	}
	
	/**
	 * 删除数据
	 * @param salesPresent
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesPresent salesPresent) {
		super.delete(salesPresent);
	}
	
}