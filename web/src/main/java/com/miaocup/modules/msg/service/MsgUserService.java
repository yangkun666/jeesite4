/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.msg.entity.MsgUser;
import com.miaocup.modules.msg.dao.MsgUserDao;

/**
 * 短信群发用户中间表Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class MsgUserService extends CrudService<MsgUserDao, MsgUser> {
	
	/**
	 * 获取单条数据
	 * @param msgUser
	 * @return
	 */
	@Override
	public MsgUser get(MsgUser msgUser) {
		return super.get(msgUser);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param msgUser
	 * @return
	 */
	@Override
	public Page<MsgUser> findPage(Page<MsgUser> page, MsgUser msgUser) {
		return super.findPage(page, msgUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param msgUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(MsgUser msgUser) {
		super.save(msgUser);
	}
	
	/**
	 * 更新状态
	 * @param msgUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(MsgUser msgUser) {
		super.updateStatus(msgUser);
	}
	
	/**
	 * 删除数据
	 * @param msgUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(MsgUser msgUser) {
		super.delete(msgUser);
	}
	
}