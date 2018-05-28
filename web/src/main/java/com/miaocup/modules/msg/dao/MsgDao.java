/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.msg.entity.Msg;

/**
 * 短信群发设置DAO接口
 * @author yangkun
 * @version 2018-03-19
 */
@MyBatisDao
public interface MsgDao extends CrudDao<Msg> {
	
}