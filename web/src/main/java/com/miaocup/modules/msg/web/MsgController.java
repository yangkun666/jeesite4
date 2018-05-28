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
import com.miaocup.modules.msg.entity.Msg;
import com.miaocup.modules.msg.service.MsgService;

/**
 * 短信群发设置Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller("messageController")
@RequestMapping(value = "${adminPath}/msg/msg")
public class MsgController extends BaseController {

	@Autowired
	private MsgService msgService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Msg get(String id, boolean isNewRecord) {
		return msgService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("msg:msg:view")
	@RequestMapping(value = {"list", ""})
	public String list(Msg msg, Model model) {
		model.addAttribute("msg", msg);
		return "modules/msg/msgList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("msg:msg:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Msg> listData(Msg msg, HttpServletRequest request, HttpServletResponse response) {
		Page<Msg> page = msgService.findPage(new Page<Msg>(request, response), msg); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("msg:msg:view")
	@RequestMapping(value = "form")
	public String form(Msg msg, Model model) {
		model.addAttribute("msg", msg);
		return "modules/msg/msgForm";
	}

	/**
	 * 保存短信群发设置
	 */
	@RequiresPermissions("msg:msg:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Msg msg) {
		msgService.save(msg);
		return renderResult(Global.TRUE, "保存短信群发设置成功！");
	}
	
	/**
	 * 停用短信群发设置
	 */
	@RequiresPermissions("msg:msg:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Msg msg) {
		msg.setStatus(Msg.STATUS_DISABLE);
		msgService.updateStatus(msg);
		return renderResult(Global.TRUE, "停用短信群发设置成功");
	}
	
	/**
	 * 启用短信群发设置
	 */
	@RequiresPermissions("msg:msg:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Msg msg) {
		msg.setStatus(Msg.STATUS_NORMAL);
		msgService.updateStatus(msg);
		return renderResult(Global.TRUE, "启用短信群发设置成功");
	}
	
	/**
	 * 删除短信群发设置
	 */
	@RequiresPermissions("msg:msg:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Msg msg) {
		msgService.delete(msg);
		return renderResult(Global.TRUE, "删除短信群发设置成功！");
	}
	
}