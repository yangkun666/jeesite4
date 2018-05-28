/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.user.entity.UserExperience;

/**
 * 会员经验配置DAO接口
 * @author yangkun
 * @version 2018-03-19
 */
@MyBatisDao
public interface UserExperienceDao extends CrudDao<UserExperience> {
	
}