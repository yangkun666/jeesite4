/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.repair.entity.Announcement;
import com.miaocup.modules.repair.dao.AnnouncementDao;

/**
 * 公告维护管理Service
 * @author yangkun
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly=true)
public class AnnouncementService extends CrudService<AnnouncementDao, Announcement> {
	
	/**
	 * 获取单条数据
	 * @param announcement
	 * @return
	 */
	@Override
	public Announcement get(Announcement announcement) {
		return super.get(announcement);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param announcement
	 * @return
	 */
	@Override
	public Page<Announcement> findPage(Page<Announcement> page, Announcement announcement) {
		return super.findPage(page, announcement);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param announcement
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Announcement announcement) {
		super.save(announcement);
	}
	
	/**
	 * 更新状态
	 * @param announcement
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Announcement announcement) {
		super.updateStatus(announcement);
	}
	
	/**
	 * 删除数据
	 * @param announcement
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Announcement announcement) {
		super.delete(announcement);
	}
	
}