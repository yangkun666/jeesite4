/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.user.entity.UserMoney;

/**
 * 用户余额明细DAO接口
 * @author yangkun
 * @version 2018-03-30
 */
@MyBatisDao
public interface UserMoneyDao extends CrudDao<UserMoney> {
	
}