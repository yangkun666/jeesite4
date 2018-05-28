/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.UserMoney;
import com.miaocup.modules.user.dao.UserMoneyDao;

/**
 * 用户余额明细Service
 * @author yangkun
 * @version 2018-03-30
 */
@Service
@Transactional(readOnly=true)
public class UserMoneyService extends CrudService<UserMoneyDao, UserMoney> {
	
	/**
	 * 获取单条数据
	 * @param userMoney
	 * @return
	 */
	@Override
	public UserMoney get(UserMoney userMoney) {
		return super.get(userMoney);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param userMoney
	 * @return
	 */
	@Override
	public Page<UserMoney> findPage(Page<UserMoney> page, UserMoney userMoney) {
		return super.findPage(page, userMoney);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param userMoney
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(UserMoney userMoney) {
		super.save(userMoney);
	}
	
	/**
	 * 更新状态
	 * @param userMoney
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(UserMoney userMoney) {
		super.updateStatus(userMoney);
	}
	
	/**
	 * 删除数据
	 * @param userMoney
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(UserMoney userMoney) {
		super.delete(userMoney);
	}
	
}