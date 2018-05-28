/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miaocup.modules.coupon.entity.Coupon;
import com.miaocup.modules.coupon.entity.UserCouponDate;
import com.miaocup.modules.coupon.service.CouponService;
import com.miaocup.modules.user.entity.UserCoupon;
import com.miaocup.modules.user.entity.UserCouponSF;
import com.miaocup.modules.user.service.UserCouponService;
import com.miaocup.uuid.UUIDUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.miaocup.modules.user.entity.ClientUser;
import com.miaocup.modules.user.service.ClientUserService;

import java.util.List;

/**
 * 会员Controller
 * @author hejun
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/user/clientUser")
public class ClientUserController extends BaseController {

	@Autowired
	private ClientUserService clientUserService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserCouponService userCouponService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ClientUser get(String id, boolean isNewRecord) {
		return clientUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:clientUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClientUser clientUser, Model model) {
		model.addAttribute("clientUser", clientUser);
		model.addAttribute("userCouponDate", new UserCouponDate());
		initSelect(model);
		return "modules/user/clientUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:clientUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ClientUser> listData(ClientUser clientUser, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(clientUser.getLastLoginTime_gte());
		System.out.println(clientUser.getLastLoginTime_lte());
		Page<ClientUser> page = clientUserService.findPage(new Page<ClientUser>(request, response), clientUser); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:clientUser:view")
	@RequestMapping(value = "form")
	public String form(ClientUser clientUser, Model model) {
		model.addAttribute("clientUser", clientUser);
		return "modules/user/clientUserForm";
	}

	/**
	 * 个人添加优惠券
	 */
	@RequiresPermissions("user:clientUser:view")
	@RequestMapping(value = "couponform")
	public String couponform(UserCouponSF couponsf, Model model) {
		model.addAttribute("couponsf", couponsf);
		return "modules/user/userCoupon";
	}
	/**
	 * 保存个人添加优惠券
	 */
	@RequiresPermissions("user:clientUser:edit")
	@PostMapping(value = "saveCoupon")
	@ResponseBody
	public String saveCoupon(@Validated UserCouponSF couponsf) {
		Coupon coupon = new Coupon();
		coupon.setEndDate(couponsf.getEndDate());
		coupon.setGrantGrade("985758811048587263");
		coupon.setMinus(couponsf.getMinus());
		coupon.setType(couponsf.getType());
		coupon.setOverPrice(couponsf.getOverPrice());
		coupon.setGetWay(couponsf.getGetWay());
		coupon.setRemarks(couponsf.getRemarks());
		couponService.save(coupon);
		for (int i = 0 ; i <couponsf.getNum() ; i++){
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCouponId(coupon.getId());
			userCoupon.setUserId(couponsf.getUserId());
			userCouponService.save(userCoupon);
		}
		return renderResult(Global.TRUE, "个人添加优惠券成功！");
	}
	/**
	 * 保存会员
	 */
	@RequiresPermissions("user:clientUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ClientUser clientUser) {
		clientUserService.save(clientUser);
		return renderResult(Global.TRUE, "保存会员成功！");
	}
	
	/**
	 * 删除会员
	 */
	@RequiresPermissions("user:clientUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ClientUser clientUser) {
		clientUserService.delete(clientUser);
		return renderResult(Global.TRUE, "删除会员成功！");
	}
	/**
	 * 初始化页面下拉框选项值
	 */
	private void initSelect(Model model) {

		Coupon coupon = new Coupon();
		coupon.setStatus("1");
		List<Coupon> couponList =couponService.findList(coupon);
		model.addAttribute("couponList", couponList);

	}
	/**
	 * 添加优惠卷
	 */
	@PostMapping(value = "saveUserCoupon")
	@ResponseBody
	public String saveUserCoupon(@Validated UserCouponDate coupon) {

		return renderResult(Global.TRUE, "保存成功！");
	}
}