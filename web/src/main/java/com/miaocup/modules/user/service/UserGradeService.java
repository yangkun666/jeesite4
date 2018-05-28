/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.UserGrade;
import com.miaocup.modules.user.dao.UserGradeDao;

/**
 * 会员等级配置Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class UserGradeService extends CrudService<UserGradeDao, UserGrade> {
	
	/**
	 * 获取单条数据
	 * @param userGrade
	 * @return
	 */
	@Override
	public UserGrade get(UserGrade userGrade) {
		return super.get(userGrade);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param userGrade
	 * @return
	 */
	@Override
	public Page<UserGrade> findPage(Page<UserGrade> page, UserGrade userGrade) {
		return super.findPage(page, userGrade);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param userGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(UserGrade userGrade) {
		super.save(userGrade);
	}
	
	/**
	 * 更新状态
	 * @param userGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(UserGrade userGrade) {
		super.updateStatus(userGrade);
	}
	
	/**
	 * 删除数据
	 * @param userGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(UserGrade userGrade) {
		super.delete(userGrade);
	}
	
}