/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.web;

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
import com.miaocup.modules.sales.entity.SalesPacket;
import com.miaocup.modules.sales.service.SalesPacketService;

/**
 * 平台红包配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesPacket")
public class SalesPacketController extends BaseController {

	@Autowired
	private SalesPacketService salesPacketService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesPacket get(String id, boolean isNewRecord) {
		return salesPacketService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesPacket:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesPacket salesPacket, Model model) {
		model.addAttribute("salesPacket", salesPacket);
		return "modules/sales/salesPacketList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesPacket:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesPacket> listData(SalesPacket salesPacket, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesPacket> page = salesPacketService.findPage(new Page<SalesPacket>(request, response), salesPacket); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesPacket:view")
	@RequestMapping(value = "form")
	public String form(SalesPacket salesPacket, Model model) {
		model.addAttribute("salesPacket", salesPacket);
		return "modules/sales/salesPacketForm";
	}

	/**
	 * 保存平台红包
	 */
	@RequiresPermissions("sales:salesPacket:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesPacket salesPacket) {
		salesPacketService.save(salesPacket);
		return renderResult(Global.TRUE, "保存平台红包成功！");
	}
	
	/**
	 * 停用平台红包
	 */
	@RequiresPermissions("sales:salesPacket:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesPacket salesPacket) {
		salesPacket.setStatus(SalesPacket.STATUS_DISABLE);
		salesPacketService.updateStatus(salesPacket);
		return renderResult(Global.TRUE, "停用平台红包成功");
	}
	
	/**
	 * 启用平台红包
	 */
	@RequiresPermissions("sales:salesPacket:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesPacket salesPacket) {
		salesPacket.setStatus(SalesPacket.STATUS_NORMAL);
		salesPacketService.updateStatus(salesPacket);
		return renderResult(Global.TRUE, "启用平台红包成功");
	}
	
	/**
	 * 删除平台红包
	 */
	@RequiresPermissions("sales:salesPacket:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesPacket salesPacket) {
		salesPacketService.delete(salesPacket);
		return renderResult(Global.TRUE, "删除平台红包成功！");
	}
	
}