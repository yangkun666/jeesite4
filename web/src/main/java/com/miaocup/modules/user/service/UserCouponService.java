/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.UserCoupon;
import com.miaocup.modules.user.dao.UserCouponDao;

/**
 * 用户优惠券Service
 * @author yangkun
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly=true)
public class UserCouponService extends CrudService<UserCouponDao, UserCoupon> {
	
	/**
	 * 获取单条数据
	 * @param userCoupon
	 * @return
	 */
	@Override
	public UserCoupon get(UserCoupon userCoupon) {
		return super.get(userCoupon);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param userCoupon
	 * @return
	 */
	@Override
	public Page<UserCoupon> findPage(Page<UserCoupon> page, UserCoupon userCoupon) {
		return super.findPage(page, userCoupon);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param userCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(UserCoupon userCoupon) {
		super.save(userCoupon);
	}
	
	/**
	 * 更新状态
	 * @param userCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(UserCoupon userCoupon) {
		super.updateStatus(userCoupon);
	}
	
	/**
	 * 删除数据
	 * @param userCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(UserCoupon userCoupon) {
		super.delete(userCoupon);
	}
	
}