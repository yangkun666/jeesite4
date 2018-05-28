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
import com.miaocup.modules.user.entity.UserStatement;
import com.miaocup.modules.user.service.UserStatementService;

/**
 * 用户流水Controller
 * @author hejun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userStatement")
public class UserStatementController extends BaseController {

	@Autowired
	private UserStatementService userStatementService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public UserStatement get(String id, boolean isNewRecord) {
		return userStatementService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:userStatement:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserStatement userStatement, Model model) {
		model.addAttribute("userStatement", userStatement);
		return "modules/user/userStatementList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:userStatement:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<UserStatement> listData(UserStatement userStatement, HttpServletRequest request, HttpServletResponse response) {
		Page<UserStatement> page = userStatementService.findPage(new Page<UserStatement>(request, response), userStatement); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:userStatement:view")
	@RequestMapping(value = "form")
	public String form(UserStatement userStatement, Model model) {
		model.addAttribute("userStatement", userStatement);
		return "modules/user/userStatementForm";
	}

	/**
	 * 保存用户流水
	 */
	@RequiresPermissions("user:userStatement:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated UserStatement userStatement) {
		userStatementService.save(userStatement);
		return renderResult(Global.TRUE, "保存用户流水成功！");
	}
	
	/**
	 * 删除用户流水
	 */
	@RequiresPermissions("user:userStatement:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(UserStatement userStatement) {
		userStatementService.delete(userStatement);
		return renderResult(Global.TRUE, "删除用户流水成功！");
	}
	
}