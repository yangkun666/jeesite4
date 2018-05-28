/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.order.entity.ApplyRefund;

/**
 * 退款申请DAO接口
 * @author yangkun
 * @version 2018-04-18
 */
@MyBatisDao
public interface ApplyRefundDao extends CrudDao<ApplyRefund> {
	
}