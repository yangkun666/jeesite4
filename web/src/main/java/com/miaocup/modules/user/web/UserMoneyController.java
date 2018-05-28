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
import com.miaocup.modules.user.entity.UserMoney;
import com.miaocup.modules.user.service.UserMoneyService;

/**
 * 用户余额明细Controller
 * @author yangkun
 * @version 2018-03-30
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userMoney")
public class UserMoneyController extends BaseController {

	@Autowired
	private UserMoneyService userMoneyService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public UserMoney get(String id, boolean isNewRecord) {
		return userMoneyService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:userMoney:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserMoney userMoney, Model model) {
		model.addAttribute("userMoney", userMoney);
		return "modules/user/userMoneyList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:userMoney:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<UserMoney> listData(UserMoney userMoney, HttpServletRequest request, HttpServletResponse response) {
		Page<UserMoney> page = userMoneyService.findPage(new Page<UserMoney>(request, response), userMoney); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:userMoney:view")
	@RequestMapping(value = "form")
	public String form(UserMoney userMoney, Model model) {
		model.addAttribute("userMoney", userMoney);
		return "modules/user/userMoneyForm";
	}

	/**
	 * 保存用户余额明细
	 */
	@RequiresPermissions("user:userMoney:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated UserMoney userMoney) {
		userMoneyService.save(userMoney);
		return renderResult(Global.TRUE, "保存用户余额明细成功！");
	}
	
	/**
	 * 停用用户余额明细
	 */
	@RequiresPermissions("user:userMoney:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(UserMoney userMoney) {
		userMoney.setStatus(UserMoney.STATUS_DISABLE);
		userMoneyService.updateStatus(userMoney);
		return renderResult(Global.TRUE, "停用用户余额明细成功");
	}
	
	/**
	 * 启用用户余额明细
	 */
	@RequiresPermissions("user:userMoney:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(UserMoney userMoney) {
		userMoney.setStatus(UserMoney.STATUS_NORMAL);
		userMoneyService.updateStatus(userMoney);
		return renderResult(Global.TRUE, "启用用户余额明细成功");
	}
	
	/**
	 * 删除用户余额明细
	 */
	@RequiresPermissions("user:userMoney:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(UserMoney userMoney) {
		userMoneyService.delete(userMoney);
		return renderResult(Global.TRUE, "删除用户余额明细成功！");
	}
	
}