/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.user.entity.ClientUser;

import java.util.List;

/**
 * 会员表DAO接口
 * @author hejun
 * @version 2018-03-13
 */
@MyBatisDao
public interface ClientUserDao extends CrudDao<ClientUser> {
    List<ClientUser> findConsumeUser();
}