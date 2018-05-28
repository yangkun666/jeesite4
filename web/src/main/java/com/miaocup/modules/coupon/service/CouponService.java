/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.coupon.service;

import java.util.List;

import com.miaocup.modules.user.dao.ClientUserDao;
import com.miaocup.modules.user.dao.UserCouponDao;
import com.miaocup.modules.user.entity.ClientUser;
import com.miaocup.modules.user.entity.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.coupon.entity.Coupon;
import com.miaocup.modules.coupon.dao.CouponDao;

/**
 * 优惠券Service
 * @author yangkun
 * @version 2018-04-13
 */
@Service
@Transactional(readOnly=true)
public class CouponService extends CrudService<CouponDao, Coupon> {


	@Autowired
	private ClientUserDao clientUserDao;
	@Autowired
	private UserCouponDao userCouponDao;
	/**
	 * 获取单条数据
	 * @param coupon
	 * @return
	 */
	@Override
	public Coupon get(Coupon coupon) {
		return super.get(coupon);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param coupon
	 * @return
	 */
	@Override
	public Page<Coupon> findPage(Page<Coupon> page, Coupon coupon) {
		return super.findPage(page, coupon);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param coupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Coupon coupon) {
		super.save(coupon);
		// 判断是否为全部用户
		ClientUser clientUser = new ClientUser();
		List<ClientUser> list = null;
		if(coupon.getGrantGrade().equals("985758811048587264")){
			list = clientUserDao.findList(clientUser);
		}else{
			clientUser.setGrade((coupon.getGrantGrade()));
			 list = clientUserDao.findList(clientUser);
		}
		//循环添加优惠卷
		for (ClientUser user :list) {
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCouponId(coupon.getId());
			userCoupon.setUserId(user.getId());
			userCoupon.setStatus("0");
			userCouponDao.insert(userCoupon);
		}
	}
	
	/**
	 * 更新状态
	 * @param coupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Coupon coupon) {
		super.updateStatus(coupon);
	}
	
	/**
	 * 删除数据
	 * @param coupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Coupon coupon) {
		super.delete(coupon);
	}
	
}