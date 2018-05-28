/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.repair.entity.Warranty;
import com.miaocup.modules.repair.dao.WarrantyDao;

/**
 * 保修有礼Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class WarrantyService extends CrudService<WarrantyDao, Warranty> {
	
	/**
	 * 获取单条数据
	 * @param warranty
	 * @return
	 */
	@Override
	public Warranty get(Warranty warranty) {
		return super.get(warranty);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param warranty
	 * @return
	 */
	@Override
	public Page<Warranty> findPage(Page<Warranty> page, Warranty warranty) {
		return super.findPage(page, warranty);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param warranty
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Warranty warranty) {
		super.save(warranty);
	}
	
	/**
	 * 更新状态
	 * @param warranty
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Warranty warranty) {
		super.updateStatus(warranty);
	}
	
	/**
	 * 删除数据
	 * @param warranty
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Warranty warranty) {
		super.delete(warranty);
	}
	
}