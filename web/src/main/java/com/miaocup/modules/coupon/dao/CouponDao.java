/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.coupon.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.coupon.entity.Coupon;

/**
 * 优惠券DAO接口
 * @author yangkun
 * @version 2018-04-13
 */
@MyBatisDao
public interface CouponDao extends CrudDao<Coupon> {
	
}