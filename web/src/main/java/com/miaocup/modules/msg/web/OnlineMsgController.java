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
import com.miaocup.modules.msg.entity.OnlineMsg;
import com.miaocup.modules.msg.service.OnlineMsgService;

/**
 * 在线留言Controller
 * @author yangkun
 * @version 2018-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/onlineMsg")
public class OnlineMsgController extends BaseController {

	@Autowired
	private OnlineMsgService onlineMsgService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OnlineMsg get(String id, boolean isNewRecord) {
		return onlineMsgService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("msg:onlineMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(OnlineMsg onlineMsg, Model model) {
		model.addAttribute("onlineMsg", onlineMsg);
		return "modules/msg/onlineMsgList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("msg:onlineMsg:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OnlineMsg> listData(OnlineMsg onlineMsg, HttpServletRequest request, HttpServletResponse response) {
		Page<OnlineMsg> page = onlineMsgService.findPage(new Page<OnlineMsg>(request, response), onlineMsg); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("msg:onlineMsg:view")
	@RequestMapping(value = "form")
	public String form(OnlineMsg onlineMsg, Model model) {
		model.addAttribute("onlineMsg", onlineMsg);
		return "modules/msg/onlineMsgForm";
	}

	/**
	 * 保存在线留言
	 */
	@RequiresPermissions("msg:onlineMsg:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OnlineMsg onlineMsg) {
		onlineMsgService.save(onlineMsg);
		return renderResult(Global.TRUE, "保存在线留言成功！");
	}
	
	/**
	 * 删除在线留言
	 */
	@RequiresPermissions("msg:onlineMsg:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OnlineMsg onlineMsg) {
		onlineMsgService.delete(onlineMsg);
		return renderResult(Global.TRUE, "删除在线留言成功！");
	}
	
}