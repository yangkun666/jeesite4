/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.repair.entity.Opinion;
import com.miaocup.modules.repair.dao.OpinionDao;

/**
 * 投诉建议Service
 * @author yangkun
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly=true)
public class OpinionService extends CrudService<OpinionDao, Opinion> {
	
	/**
	 * 获取单条数据
	 * @param opinion
	 * @return
	 */
	@Override
	public Opinion get(Opinion opinion) {
		return super.get(opinion);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param opinion
	 * @return
	 */
	@Override
	public Page<Opinion> findPage(Page<Opinion> page, Opinion opinion) {
		return super.findPage(page, opinion);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param opinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Opinion opinion) {
		super.save(opinion);
	}
	
	/**
	 * 更新状态
	 * @param opinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Opinion opinion) {
		super.updateStatus(opinion);
	}
	
	/**
	 * 删除数据
	 * @param opinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Opinion opinion) {
		super.delete(opinion);
	}
	
}