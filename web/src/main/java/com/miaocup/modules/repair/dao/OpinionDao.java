/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.repair.entity.Opinion;

/**
 * 投诉建议DAO接口
 * @author yangkun
 * @version 2018-04-11
 */
@MyBatisDao
public interface OpinionDao extends CrudDao<Opinion> {
	
}