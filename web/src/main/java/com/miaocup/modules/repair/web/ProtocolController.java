/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.repair.web;

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
import com.miaocup.modules.repair.entity.Protocol;
import com.miaocup.modules.repair.service.ProtocolService;

/**
 * 协议内容维护管理Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/repair/protocol")
public class ProtocolController extends BaseController {

	@Autowired
	private ProtocolService protocolService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Protocol get(String id, boolean isNewRecord) {
		return protocolService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("repair:protocol:view")
	@RequestMapping(value = {"list", ""})
	public String list(Protocol protocol, Model model) {
		model.addAttribute("protocol", protocol);
		return "modules/repair/protocolList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("repair:protocol:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Protocol> listData(Protocol protocol, HttpServletRequest request, HttpServletResponse response) {
		Page<Protocol> page = protocolService.findPage(new Page<Protocol>(request, response), protocol); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("repair:protocol:view")
	@RequestMapping(value = "form")
	public String form(Protocol protocol, Model model) {
		model.addAttribute("protocol", protocol);
		return "modules/repair/protocolForm";
	}

	/**
	 * 保存协议内容维护管理
	 */
	@RequiresPermissions("repair:protocol:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Protocol protocol) {
		protocolService.save(protocol);
		return renderResult(Global.TRUE, "保存协议内容维护管理成功！");
	}
	
	/**
	 * 停用协议内容维护管理
	 */
	@RequiresPermissions("repair:protocol:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Protocol protocol) {
		protocol.setStatus(Protocol.STATUS_DISABLE);
		protocolService.updateStatus(protocol);
		return renderResult(Global.TRUE, "停用协议内容维护管理成功");
	}
	
	/**
	 * 启用协议内容维护管理
	 */
	@RequiresPermissions("repair:protocol:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Protocol protocol) {
		protocol.setStatus(Protocol.STATUS_NORMAL);
		protocolService.updateStatus(protocol);
		return renderResult(Global.TRUE, "启用协议内容维护管理成功");
	}
	
	/**
	 * 删除协议内容维护管理
	 */
	@RequiresPermissions("repair:protocol:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Protocol protocol) {
		protocolService.delete(protocol);
		return renderResult(Global.TRUE, "删除协议内容维护管理成功！");
	}
	
}