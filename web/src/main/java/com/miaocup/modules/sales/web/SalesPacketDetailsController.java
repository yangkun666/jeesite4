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
import com.miaocup.modules.sales.entity.SalesPacketDetails;
import com.miaocup.modules.sales.service.SalesPacketDetailsService;

/**
 * 平台红包详情Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesPacketDetails")
public class SalesPacketDetailsController extends BaseController {

	@Autowired
	private SalesPacketDetailsService salesPacketDetailsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesPacketDetails get(String id, String packetId, boolean isNewRecord) {
		return salesPacketDetailsService.get(new Class<?>[]{String.class, String.class},
				new Object[]{id, packetId}, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesPacketDetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesPacketDetails salesPacketDetails, Model model) {
		model.addAttribute("salesPacketDetails", salesPacketDetails);
		return "modules/sales/salesPacketDetailsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesPacketDetails:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesPacketDetails> listData(SalesPacketDetails salesPacketDetails, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesPacketDetails> page = salesPacketDetailsService.findPage(new Page<SalesPacketDetails>(request, response), salesPacketDetails); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesPacketDetails:view")
	@RequestMapping(value = "form")
	public String form(SalesPacketDetails salesPacketDetails, Model model) {
		model.addAttribute("salesPacketDetails", salesPacketDetails);
		return "modules/sales/salesPacketDetailsForm";
	}

	/**
	 * 保存平台红包详情
	 */
	@RequiresPermissions("sales:salesPacketDetails:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesPacketDetails salesPacketDetails) {
		salesPacketDetailsService.save(salesPacketDetails);
		return renderResult(Global.TRUE, "保存平台红包详情成功！");
	}
	
	/**
	 * 停用平台红包详情
	 */
	@RequiresPermissions("sales:salesPacketDetails:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesPacketDetails salesPacketDetails) {
		salesPacketDetails.setStatus(SalesPacketDetails.STATUS_DISABLE);
		salesPacketDetailsService.updateStatus(salesPacketDetails);
		return renderResult(Global.TRUE, "停用平台红包详情成功");
	}
	
	/**
	 * 启用平台红包详情
	 */
	@RequiresPermissions("sales:salesPacketDetails:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesPacketDetails salesPacketDetails) {
		salesPacketDetails.setStatus(SalesPacketDetails.STATUS_NORMAL);
		salesPacketDetailsService.updateStatus(salesPacketDetails);
		return renderResult(Global.TRUE, "启用平台红包详情成功");
	}
	
	/**
	 * 删除平台红包详情
	 */
	@RequiresPermissions("sales:salesPacketDetails:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesPacketDetails salesPacketDetails) {
		salesPacketDetailsService.delete(salesPacketDetails);
		return renderResult(Global.TRUE, "删除平台红包详情成功！");
	}
	
}