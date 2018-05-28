/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.user.entity.UserCoupon;

/**
 * 用户优惠券DAO接口
 * @author yangkun
 * @version 2018-04-01
 */
@MyBatisDao
public interface UserCouponDao extends CrudDao<UserCoupon> {
	
}