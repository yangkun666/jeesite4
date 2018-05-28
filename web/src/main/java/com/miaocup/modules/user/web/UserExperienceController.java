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
import com.miaocup.modules.user.entity.UserExperience;
import com.miaocup.modules.user.service.UserExperienceService;

/**
 * 会员经验配置Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userExperience")
public class UserExperienceController extends BaseController {

	@Autowired
	private UserExperienceService userExperienceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public UserExperience get(String id, boolean isNewRecord) {
		return userExperienceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:userExperience:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserExperience userExperience, Model model) {
		model.addAttribute("userExperience", userExperience);
		return "modules/user/userExperienceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:userExperience:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<UserExperience> listData(UserExperience userExperience, HttpServletRequest request, HttpServletResponse response) {
		Page<UserExperience> page = userExperienceService.findPage(new Page<UserExperience>(request, response), userExperience); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:userExperience:view")
	@RequestMapping(value = "form")
	public String form(UserExperience userExperience, Model model) {
		model.addAttribute("userExperience", userExperience);
		return "modules/user/userExperienceForm";
	}

	/**
	 * 保存会员经验配置
	 */
	@RequiresPermissions("user:userExperience:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated UserExperience userExperience) {
		userExperienceService.save(userExperience);
		return renderResult(Global.TRUE, "保存会员经验配置成功！");
	}
	
	/**
	 * 停用会员经验配置
	 */
	@RequiresPermissions("user:userExperience:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(UserExperience userExperience) {
		userExperience.setStatus(UserExperience.STATUS_DISABLE);
		userExperienceService.updateStatus(userExperience);
		return renderResult(Global.TRUE, "停用会员经验配置成功");
	}
	
	/**
	 * 启用会员经验配置
	 */
	@RequiresPermissions("user:userExperience:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(UserExperience userExperience) {
		userExperience.setStatus(UserExperience.STATUS_NORMAL);
		userExperienceService.updateStatus(userExperience);
		return renderResult(Global.TRUE, "启用会员经验配置成功");
	}
	
	/**
	 * 删除会员经验配置
	 */
	@RequiresPermissions("user:userExperience:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(UserExperience userExperience) {
		userExperienceService.delete(userExperience);
		return renderResult(Global.TRUE, "删除会员经验配置成功！");
	}
	
}