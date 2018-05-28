/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.cm.entity.CashDeposit;

/**
 * 咖啡机保证金DAO接口
 * @author hejun
 * @version 2018-03-18
 */
@MyBatisDao
public interface CashDepositDao extends CrudDao<CashDeposit> {
	
}