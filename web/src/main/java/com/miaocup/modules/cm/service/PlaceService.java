/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.Place;
import com.miaocup.modules.cm.dao.PlaceDao;

/**
 * 场地管理Service
 * @author hejun
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly=true)
public class PlaceService extends CrudService<PlaceDao, Place> {
	
	/**
	 * 获取单条数据
	 * @param place
	 * @return
	 */
	@Override
	public Place get(Place place) {
		return super.get(place);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param place
	 * @return
	 */
	@Override
	public Page<Place> findPage(Page<Place> page, Place place) {
		return super.findPage(page, place);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param place
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Place place) {
		super.save(place);
	}
	
	/**
	 * 更新状态
	 * @param place
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Place place) {
		super.updateStatus(place);
	}
	
	/**
	 * 删除数据
	 * @param place
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Place place) {
		super.delete(place);
	}
	
}