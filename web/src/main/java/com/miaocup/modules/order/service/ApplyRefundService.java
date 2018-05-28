/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.order.entity.ApplyRefund;
import com.miaocup.modules.order.dao.ApplyRefundDao;

/**
 * 退款申请Service
 * @author yangkun
 * @version 2018-04-18
 */
@Service
@Transactional(readOnly=true)
public class ApplyRefundService extends CrudService<ApplyRefundDao, ApplyRefund> {
	
	/**
	 * 获取单条数据
	 * @param applyRefund
	 * @return
	 */
	@Override
	public ApplyRefund get(ApplyRefund applyRefund) {
		return super.get(applyRefund);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param applyRefund
	 * @return
	 */
	@Override
	public Page<ApplyRefund> findPage(Page<ApplyRefund> page, ApplyRefund applyRefund) {
		return super.findPage(page, applyRefund);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param applyRefund
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ApplyRefund applyRefund) {
		super.save(applyRefund);
	}
	
	/**
	 * 更新状态
	 * @param applyRefund
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ApplyRefund applyRefund) {
		super.updateStatus(applyRefund);
	}
	
	/**
	 * 删除数据
	 * @param applyRefund
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ApplyRefund applyRefund) {
		super.delete(applyRefund);
	}
	
}