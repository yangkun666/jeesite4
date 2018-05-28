/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.order.entity.OrderInfo;
import com.miaocup.modules.order.dao.OrderInfoDao;

/**
 * 订单管理Service
 * @author yangkun
 * @version 2018-04-16
 */
@Service
@Transactional(readOnly=true)
public class OrderInfoService extends CrudService<OrderInfoDao, OrderInfo> {
	
	/**
	 * 获取单条数据
	 * @param orderInfo
	 * @return
	 */
	@Override
	public OrderInfo get(OrderInfo orderInfo) {
		return super.get(orderInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param orderInfo
	 * @return
	 */
	@Override
	public Page<OrderInfo> findPage(Page<OrderInfo> page, OrderInfo orderInfo) {
		return super.findPage(page, orderInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param orderInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OrderInfo orderInfo) {
		super.save(orderInfo);
	}
	
	/**
	 * 更新状态
	 * @param orderInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OrderInfo orderInfo) {
		super.updateStatus(orderInfo);
	}
	
	/**
	 * 删除数据
	 * @param orderInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OrderInfo orderInfo) {
		super.delete(orderInfo);
	}
	
}