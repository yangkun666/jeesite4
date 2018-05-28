/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.UserExperience;
import com.miaocup.modules.user.dao.UserExperienceDao;

/**
 * 会员经验配置Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class UserExperienceService extends CrudService<UserExperienceDao, UserExperience> {
	
	/**
	 * 获取单条数据
	 * @param userExperience
	 * @return
	 */
	@Override
	public UserExperience get(UserExperience userExperience) {
		return super.get(userExperience);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param userExperience
	 * @return
	 */
	@Override
	public Page<UserExperience> findPage(Page<UserExperience> page, UserExperience userExperience) {
		return super.findPage(page, userExperience);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param userExperience
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(UserExperience userExperience) {
		super.save(userExperience);
	}
	
	/**
	 * 更新状态
	 * @param userExperience
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(UserExperience userExperience) {
		super.updateStatus(userExperience);
	}
	
	/**
	 * 删除数据
	 * @param userExperience
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(UserExperience userExperience) {
		super.delete(userExperience);
	}
	
}