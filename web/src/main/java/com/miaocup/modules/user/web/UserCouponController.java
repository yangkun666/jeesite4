/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.miaocup.modules.user.entity.UserCoupon;
import com.miaocup.modules.user.service.UserCouponService;

/**
 * 用户优惠券Controller
 * @author yangkun
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userCoupon")
public class UserCouponController extends BaseController {

	@Autowired
	private UserCouponService userCouponService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public UserCoupon get(String id, boolean isNewRecord) {
		return userCouponService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:userCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserCoupon userCoupon, Model model) {
		model.addAttribute("userCoupon", userCoupon);
		return "modules/user/userCouponList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:userCoupon:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<UserCoupon> listData(UserCoupon userCoupon, HttpServletRequest request, HttpServletResponse response) {
		Page<UserCoupon> page = userCouponService.findPage(new Page<UserCoupon>(request, response), userCoupon); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:userCoupon:view")
	@RequestMapping(value = "form")
	public String form(UserCoupon userCoupon, Model model) {
		model.addAttribute("userCoupon", userCoupon);
		return "modules/user/userCouponForm";
	}

	/**
	 * 保存用户优惠券
	 */
	@RequiresPermissions("user:userCoupon:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated UserCoupon userCoupon) {
		userCouponService.save(userCoupon);
		return renderResult(Global.TRUE, "保存用户优惠券成功！");
	}
	
	/**
	 * 停用用户优惠券
	 */
	@RequiresPermissions("user:userCoupon:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(UserCoupon userCoupon) {
		userCoupon.setStatus(UserCoupon.STATUS_DISABLE);
		userCouponService.updateStatus(userCoupon);
		return renderResult(Global.TRUE, "停用用户优惠券成功");
	}
	
	/**
	 * 启用用户优惠券
	 */
	@RequiresPermissions("user:userCoupon:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(UserCoupon userCoupon) {
		userCoupon.setStatus(UserCoupon.STATUS_NORMAL);
		userCouponService.updateStatus(userCoupon);
		return renderResult(Global.TRUE, "启用用户优惠券成功");
	}
	
	/**
	 * 删除用户优惠券
	 */
	@RequiresPermissions("user:userCoupon:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(UserCoupon userCoupon) {
		userCouponService.delete(userCoupon);
		return renderResult(Global.TRUE, "删除用户优惠券成功！");
	}
	
}