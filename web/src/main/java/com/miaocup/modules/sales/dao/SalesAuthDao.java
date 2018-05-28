/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.sales.entity.SalesAuth;

/**
 * 促销活动授权配置DAO接口
 * @author yangkun
 * @version 2018-03-31
 */
@MyBatisDao
public interface SalesAuthDao extends CrudDao<SalesAuth> {
	
}