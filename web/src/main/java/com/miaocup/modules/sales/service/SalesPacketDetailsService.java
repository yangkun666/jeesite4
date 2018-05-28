/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.sales.entity.SalesPacketDetails;
import com.miaocup.modules.sales.dao.SalesPacketDetailsDao;

/**
 * 平台红包详情Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class SalesPacketDetailsService extends CrudService<SalesPacketDetailsDao, SalesPacketDetails> {
	
	/**
	 * 获取单条数据
	 * @param salesPacketDetails
	 * @return
	 */
	@Override
	public SalesPacketDetails get(SalesPacketDetails salesPacketDetails) {
		return super.get(salesPacketDetails);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param salesPacketDetails
	 * @return
	 */
	@Override
	public Page<SalesPacketDetails> findPage(Page<SalesPacketDetails> page, SalesPacketDetails salesPacketDetails) {
		return super.findPage(page, salesPacketDetails);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param salesPacketDetails
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SalesPacketDetails salesPacketDetails) {
		super.save(salesPacketDetails);
	}
	
	/**
	 * 更新状态
	 * @param salesPacketDetails
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SalesPacketDetails salesPacketDetails) {
		super.updateStatus(salesPacketDetails);
	}
	
	/**
	 * 删除数据
	 * @param salesPacketDetails
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SalesPacketDetails salesPacketDetails) {
		super.delete(salesPacketDetails);
	}
	
}