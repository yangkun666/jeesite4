/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.CashDeposit;
import com.miaocup.modules.cm.dao.CashDepositDao;

/**
 * 咖啡机保证金Service
 * @author hejun
 * @version 2018-03-18
 */
@Service
@Transactional(readOnly=true)
public class CashDepositService extends CrudService<CashDepositDao, CashDeposit> {
	
	/**
	 * 获取单条数据
	 * @param cashDeposit
	 * @return
	 */
	@Override
	public CashDeposit get(CashDeposit cashDeposit) {
		return super.get(cashDeposit);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param cashDeposit
	 * @return
	 */
	@Override
	public Page<CashDeposit> findPage(Page<CashDeposit> page, CashDeposit cashDeposit) {
		return super.findPage(page, cashDeposit);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param cashDeposit
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CashDeposit cashDeposit) {
		super.save(cashDeposit);
	}
	
	/**
	 * 更新状态
	 * @param cashDeposit
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CashDeposit cashDeposit) {
		super.updateStatus(cashDeposit);
	}
	
	/**
	 * 删除数据
	 * @param cashDeposit
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CashDeposit cashDeposit) {
		super.delete(cashDeposit);
	}
	
}