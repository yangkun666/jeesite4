/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.client.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.client.entity.Banner;

/**
 * 广告管理DAO接口
 * @author yangkun
 * @version 2018-03-14
 */
@MyBatisDao
public interface BannerDao extends CrudDao<Banner> {
	
}