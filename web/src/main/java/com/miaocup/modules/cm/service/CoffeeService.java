/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.Coffee;
import com.miaocup.modules.cm.dao.CoffeeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 咖啡品种Service
 * @author hejun
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly=true)
public class CoffeeService extends CrudService<CoffeeDao, Coffee> {
	
	/**
	 * 获取单条数据
	 * @param coffee
	 * @return
	 */
	@Override
	public Coffee get(Coffee coffee) {
		return super.get(coffee);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param coffee
	 * @return
	 */
	@Override
	public Page<Coffee> findPage(Page<Coffee> page, Coffee coffee) {
		return super.findPage(page, coffee);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param coffee
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Coffee coffee) {
		super.save(coffee);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(coffee.getId(), "images");
	}
	
	/**
	 * 更新状态
	 * @param coffee
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Coffee coffee) {
		super.updateStatus(coffee);
	}
	
	/**
	 * 删除数据
	 * @param coffee
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Coffee coffee) {
		super.delete(coffee);
	}
	
}