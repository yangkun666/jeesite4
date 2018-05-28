/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.user.entity.UserStatement;

/**
 * 用户流水DAO接口
 * @author hejun
 * @version 2018-03-14
 */
@MyBatisDao
public interface UserStatementDao extends CrudDao<UserStatement> {
	
}