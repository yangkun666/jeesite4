/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.repair.entity.Protocol;
import com.miaocup.modules.repair.dao.ProtocolDao;

/**
 * 协议内容维护管理Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class ProtocolService extends CrudService<ProtocolDao, Protocol> {
	
	/**
	 * 获取单条数据
	 * @param protocol
	 * @return
	 */
	@Override
	public Protocol get(Protocol protocol) {
		return super.get(protocol);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param protocol
	 * @return
	 */
	@Override
	public Page<Protocol> findPage(Page<Protocol> page, Protocol protocol) {
		return super.findPage(page, protocol);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param protocol
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Protocol protocol) {
		super.save(protocol);
	}
	
	/**
	 * 更新状态
	 * @param protocol
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Protocol protocol) {
		super.updateStatus(protocol);
	}
	
	/**
	 * 删除数据
	 * @param protocol
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Protocol protocol) {
		super.delete(protocol);
	}
	
}