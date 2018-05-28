/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.coupon.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miaocup.modules.user.entity.UserGrade;
import com.miaocup.modules.user.service.UserGradeService;
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
import com.miaocup.modules.coupon.entity.Coupon;
import com.miaocup.modules.coupon.service.CouponService;

import java.util.List;

/**
 * 优惠券Controller
 * @author yangkun
 * @version 2018-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/coupon")
public class CouponController extends BaseController {

	@Autowired
	private CouponService couponService;
	@Autowired
	private UserGradeService userGradeService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Coupon get(String id, boolean isNewRecord) {
		return couponService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(Coupon coupon, Model model) {
		this.initSelect(model);
		model.addAttribute("coupon", coupon);
		return "modules/coupon/couponList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Coupon> listData(Coupon coupon, HttpServletRequest request, HttpServletResponse response) {
		Page<Coupon> page = couponService.findPage(new Page<Coupon>(request, response), coupon); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = "form")
	public String form(Coupon coupon, Model model) {
		this.initSelect(model);
		model.addAttribute("coupon", coupon);
		return "modules/coupon/couponForm";
	}

	/**
	 * 保存优惠券
	 */
	@RequiresPermissions("coupon:coupon:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Coupon coupon) {
		couponService.save(coupon);
		return renderResult(Global.TRUE, "保存优惠券成功！");
	}
	
	/**
	 * 停用优惠券
	 */
	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Coupon coupon) {
		coupon.setStatus(Coupon.STATUS_DISABLE);
		couponService.updateStatus(coupon);
		return renderResult(Global.TRUE, "停用优惠券成功");
	}
	
	/**
	 * 启用优惠券
	 */
	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Coupon coupon) {
		coupon.setStatus(Coupon.STATUS_NORMAL);
		couponService.updateStatus(coupon);
		return renderResult(Global.TRUE, "启用优惠券成功");
	}
	
	/**
	 * 删除优惠券
	 */
	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Coupon coupon) {
		couponService.delete(coupon);
		return renderResult(Global.TRUE, "删除优惠券成功！");
	}
	/**
	 * 初始化页面下拉框选项值
	 */
	private void initSelect(Model model) {
		UserGrade userGrade = new UserGrade();
		List<UserGrade> userGradeList = userGradeService.findList(new UserGrade());
		userGrade.setName("全部");
		userGrade.setId("985758811048587264");
		userGradeList.add(0,userGrade );
		model.addAttribute("userGradeList", userGradeList);
	}
}