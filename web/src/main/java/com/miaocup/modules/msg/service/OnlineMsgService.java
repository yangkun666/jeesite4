/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.msg.entity.OnlineMsg;
import com.miaocup.modules.msg.dao.OnlineMsgDao;

/**
 * 在线留言Service
 * @author yangkun
 * @version 2018-04-17
 */
@Service
@Transactional(readOnly=true)
public class OnlineMsgService extends CrudService<OnlineMsgDao, OnlineMsg> {
	
	/**
	 * 获取单条数据
	 * @param onlineMsg
	 * @return
	 */
	@Override
	public OnlineMsg get(OnlineMsg onlineMsg) {
		return super.get(onlineMsg);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param onlineMsg
	 * @return
	 */
	@Override
	public Page<OnlineMsg> findPage(Page<OnlineMsg> page, OnlineMsg onlineMsg) {
		return super.findPage(page, onlineMsg);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param onlineMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OnlineMsg onlineMsg) {
		super.save(onlineMsg);
	}
	
	/**
	 * 更新状态
	 * @param onlineMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OnlineMsg onlineMsg) {
		super.updateStatus(onlineMsg);
	}
	
	/**
	 * 删除数据
	 * @param onlineMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OnlineMsg onlineMsg) {
		super.delete(onlineMsg);
	}
	
}