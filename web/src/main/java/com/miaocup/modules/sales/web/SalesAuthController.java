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
import com.miaocup.modules.sales.entity.SalesAuth;
import com.miaocup.modules.sales.service.SalesAuthService;

/**
 * 促销活动授权配置Controller
 * @author yangkun
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesAuth")
public class SalesAuthController extends BaseController {

	@Autowired
	private SalesAuthService salesAuthService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesAuth get(String id, boolean isNewRecord) {
		return salesAuthService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesAuth:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesAuth salesAuth, Model model) {
		model.addAttribute("salesAuth", salesAuth);
		return "modules/sales/salesAuthList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesAuth:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesAuth> listData(SalesAuth salesAuth, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesAuth> page = salesAuthService.findPage(new Page<SalesAuth>(request, response), salesAuth); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesAuth:view")
	@RequestMapping(value = "form")
	public String form(SalesAuth salesAuth, Model model) {
		model.addAttribute("salesAuth", salesAuth);
		return "modules/sales/salesAuthForm";
	}

	/**
	 * 保存促销活动授权配置
	 */
	@RequiresPermissions("sales:salesAuth:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesAuth salesAuth) {
		salesAuthService.save(salesAuth);
		return renderResult(Global.TRUE, "保存促销活动授权配置成功！");
	}
	
	/**
	 * 停用促销活动授权配置
	 */
	@RequiresPermissions("sales:salesAuth:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesAuth salesAuth) {
		salesAuth.setStatus(SalesAuth.STATUS_DISABLE);
		salesAuthService.updateStatus(salesAuth);
		return renderResult(Global.TRUE, "停用促销活动授权配置成功");
	}
	
	/**
	 * 启用促销活动授权配置
	 */
	@RequiresPermissions("sales:salesAuth:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesAuth salesAuth) {
		salesAuth.setStatus(SalesAuth.STATUS_NORMAL);
		salesAuthService.updateStatus(salesAuth);
		return renderResult(Global.TRUE, "启用促销活动授权配置成功");
	}
	
	/**
	 * 删除促销活动授权配置
	 */
	@RequiresPermissions("sales:salesAuth:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesAuth salesAuth) {
		salesAuthService.delete(salesAuth);
		return renderResult(Global.TRUE, "删除促销活动授权配置成功！");
	}
	
}