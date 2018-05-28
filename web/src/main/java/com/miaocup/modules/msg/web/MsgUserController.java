/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.web;

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
import com.miaocup.modules.msg.entity.MsgUser;
import com.miaocup.modules.msg.service.MsgUserService;

/**
 * 短信群发用户中间表Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/msgUser")
public class MsgUserController extends BaseController {

	@Autowired
	private MsgUserService msgUserService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public MsgUser get(String id, boolean isNewRecord) {
		return msgUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("msg:msgUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsgUser msgUser, Model model) {
		model.addAttribute("msgUser", msgUser);
		return "modules/msg/msgUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("msg:msgUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<MsgUser> listData(MsgUser msgUser, HttpServletRequest request, HttpServletResponse response) {
		Page<MsgUser> page = msgUserService.findPage(new Page<MsgUser>(request, response), msgUser); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("msg:msgUser:view")
	@RequestMapping(value = "form")
	public String form(MsgUser msgUser, Model model) {
		model.addAttribute("msgUser", msgUser);
		return "modules/msg/msgUserForm";
	}

	/**
	 * 保存短信群发用户中间表
	 */
	@RequiresPermissions("msg:msgUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated MsgUser msgUser) {
		msgUserService.save(msgUser);
		return renderResult(Global.TRUE, "保存短信群发用户中间表成功！");
	}
	
	/**
	 * 停用短信群发用户中间表
	 */
	@RequiresPermissions("msg:msgUser:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(MsgUser msgUser) {
		msgUser.setStatus(MsgUser.STATUS_DISABLE);
		msgUserService.updateStatus(msgUser);
		return renderResult(Global.TRUE, "停用短信群发用户中间表成功");
	}
	
	/**
	 * 启用短信群发用户中间表
	 */
	@RequiresPermissions("msg:msgUser:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(MsgUser msgUser) {
		msgUser.setStatus(MsgUser.STATUS_NORMAL);
		msgUserService.updateStatus(msgUser);
		return renderResult(Global.TRUE, "启用短信群发用户中间表成功");
	}
	
	/**
	 * 删除短信群发用户中间表
	 */
	@RequiresPermissions("msg:msgUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(MsgUser msgUser) {
		msgUserService.delete(msgUser);
		return renderResult(Global.TRUE, "删除短信群发用户中间表成功！");
	}
	
}