/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.repair.entity.Announcement;

/**
 * 公告维护管理DAO接口
 * @author yangkun
 * @version 2018-03-19
 */
@MyBatisDao
public interface AnnouncementDao extends CrudDao<Announcement> {
	
}