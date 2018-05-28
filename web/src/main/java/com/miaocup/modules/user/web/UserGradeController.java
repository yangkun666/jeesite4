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
import com.miaocup.modules.user.entity.UserGrade;
import com.miaocup.modules.user.service.UserGradeService;

/**
 * 会员等级配置Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userGrade")
public class UserGradeController extends BaseController {

	@Autowired
	private UserGradeService userGradeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public UserGrade get(String id, boolean isNewRecord) {
		return userGradeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:userGrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserGrade userGrade, Model model) {
		model.addAttribute("userGrade", userGrade);
		return "modules/user/userGradeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:userGrade:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<UserGrade> listData(UserGrade userGrade, HttpServletRequest request, HttpServletResponse response) {
		Page<UserGrade> page = userGradeService.findPage(new Page<UserGrade>(request, response), userGrade); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:userGrade:view")
	@RequestMapping(value = "form")
	public String form(UserGrade userGrade, Model model) {
		model.addAttribute("userGrade", userGrade);
		return "modules/user/userGradeForm";
	}

	/**
	 * 保存会员等级配置
	 */
	@RequiresPermissions("user:userGrade:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated UserGrade userGrade) {
		userGradeService.save(userGrade);
		return renderResult(Global.TRUE, "保存会员等级配置成功！");
	}
	
	/**
	 * 停用会员等级配置
	 */
	@RequiresPermissions("user:userGrade:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(UserGrade userGrade) {
		userGrade.setStatus(UserGrade.STATUS_DISABLE);
		userGradeService.updateStatus(userGrade);
		return renderResult(Global.TRUE, "停用会员等级配置成功");
	}
	
	/**
	 * 启用会员等级配置
	 */
	@RequiresPermissions("user:userGrade:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(UserGrade userGrade) {
		userGrade.setStatus(UserGrade.STATUS_NORMAL);
		userGradeService.updateStatus(userGrade);
		return renderResult(Global.TRUE, "启用会员等级配置成功");
	}
	
	/**
	 * 删除会员等级配置
	 */
	@RequiresPermissions("user:userGrade:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(UserGrade userGrade) {
		userGradeService.delete(userGrade);
		return renderResult(Global.TRUE, "删除会员等级配置成功！");
	}
	
}