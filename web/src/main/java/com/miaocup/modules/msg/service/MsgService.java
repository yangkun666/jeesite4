/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.msg.entity.Msg;
import com.miaocup.modules.msg.dao.MsgDao;

/**
 * 短信群发设置Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class MsgService extends CrudService<MsgDao, Msg> {
	
	/**
	 * 获取单条数据
	 * @param msg
	 * @return
	 */
	@Override
	public Msg get(Msg msg) {
		return super.get(msg);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param msg
	 * @return
	 */
	@Override
	public Page<Msg> findPage(Page<Msg> page, Msg msg) {
		return super.findPage(page, msg);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param msg
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Msg msg) {
		super.save(msg);
	}
	
	/**
	 * 更新状态
	 * @param msg
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Msg msg) {
		super.updateStatus(msg);
	}
	
	/**
	 * 删除数据
	 * @param msg
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Msg msg) {
		super.delete(msg);
	}
	
}