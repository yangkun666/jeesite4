/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.cm.entity.Coffee;

/**
 * 咖啡品种DAO接口
 * @author hejun
 * @version 2018-03-17
 */
@MyBatisDao
public interface CoffeeDao extends CrudDao<Coffee> {
	
}