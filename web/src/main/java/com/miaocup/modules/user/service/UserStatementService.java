/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.UserStatement;
import com.miaocup.modules.user.dao.UserStatementDao;

/**
 * 用户流水Service
 * @author hejun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class UserStatementService extends CrudService<UserStatementDao, UserStatement> {
	
	/**
	 * 获取单条数据
	 * @param userStatement
	 * @return
	 */
	@Override
	public UserStatement get(UserStatement userStatement) {
		return super.get(userStatement);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param userStatement
	 * @return
	 */
	@Override
	public Page<UserStatement> findPage(Page<UserStatement> page, UserStatement userStatement) {
		return super.findPage(page, userStatement);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param userStatement
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(UserStatement userStatement) {
		super.save(userStatement);
	}
	
	/**
	 * 更新状态
	 * @param userStatement
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(UserStatement userStatement) {
		super.updateStatus(userStatement);
	}
	
	/**
	 * 删除数据
	 * @param userStatement
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(UserStatement userStatement) {
		super.delete(userStatement);
	}
	
}