/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesPacket;
import com.miaocup.modules.sales.dao.SalesPacketDao;

/**
 * 平台红包配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesPacketService extends CrudService<SalesPacketDao, SalesPacket> {
	
	/**
	 * 获取单条数据
	 * @param salesPacket
	 * @return
	 */
	@Override
	public SalesPacket get(SalesPacket salesPacket) {
		return super.get(salesPacket);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesPacket
	 * @return
	 */
	@Override
	public Page<SalesPacket> findPage(Page<SalesPacket> page, SalesPacket salesPacket) {
		return super.findPage(page, salesPacket);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesPacket
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesPacket salesPacket) {
		super.save(salesPacket);
	}
	
	/**
	 * 更新状态
	 * @param salesPacket
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesPacket salesPacket) {
		super.updateStatus(salesPacket);
	}
	
	/**
	 * 删除数据
	 * @param salesPacket
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesPacket salesPacket) {
		super.delete(salesPacket);
	}
	
}