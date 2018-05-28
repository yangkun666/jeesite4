/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.msg.entity.OnlineMsg;

/**
 * 在线留言DAO接口
 * @author yangkun
 * @version 2018-04-17
 */
@MyBatisDao
public interface OnlineMsgDao extends CrudDao<OnlineMsg> {
	
}