/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesAuth;
import com.miaocup.modules.sales.dao.SalesAuthDao;

/**
 * 促销活动授权配置Service
 * @author yangkun
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly=true)
public class SalesAuthService extends CrudService<SalesAuthDao, SalesAuth> {
	
	/**
	 * 获取单条数据
	 * @param salesAuth
	 * @return
	 */
	@Override
	public SalesAuth get(SalesAuth salesAuth) {
		return super.get(salesAuth);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesAuth
	 * @return
	 */
	@Override
	public Page<SalesAuth> findPage(Page<SalesAuth> page, SalesAuth salesAuth) {
		return super.findPage(page, salesAuth);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesAuth
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesAuth salesAuth) {
		super.save(salesAuth);
	}
	
	/**
	 * 更新状态
	 * @param salesAuth
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesAuth salesAuth) {
		super.updateStatus(salesAuth);
	}
	
	/**
	 * 删除数据
	 * @param salesAuth
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesAuth salesAuth) {
		super.delete(salesAuth);
	}
	
}