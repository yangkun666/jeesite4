/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.ApplyTry;
import com.miaocup.modules.cm.dao.ApplyTryDao;

/**
 * 申请试用Service
 * @author yangkun
 * @version 2018-04-17
 */
@Service
@Transactional(readOnly=true)
public class ApplyTryService extends CrudService<ApplyTryDao, ApplyTry> {
	
	/**
	 * 获取单条数据
	 * @param applyTry
	 * @return
	 */
	@Override
	public ApplyTry get(ApplyTry applyTry) {
		return super.get(applyTry);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param applyTry
	 * @return
	 */
	@Override
	public Page<ApplyTry> findPage(Page<ApplyTry> page, ApplyTry applyTry) {
		return super.findPage(page, applyTry);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param applyTry
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ApplyTry applyTry) {
		super.save(applyTry);
	}
	
	/**
	 * 更新状态
	 * @param applyTry
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ApplyTry applyTry) {
		super.updateStatus(applyTry);
	}
	
	/**
	 * 删除数据
	 * @param applyTry
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ApplyTry applyTry) {
		super.delete(applyTry);
	}
	
}