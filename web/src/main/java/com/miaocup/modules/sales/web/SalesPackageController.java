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
import com.miaocup.modules.sales.entity.SalesPackage;
import com.miaocup.modules.sales.service.SalesPackageService;

/**
 * 优惠套餐配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesPackage")
public class SalesPackageController extends BaseController {

	@Autowired
	private SalesPackageService salesPackageService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesPackage get(String id, boolean isNewRecord) {
		return salesPackageService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesPackage:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesPackage salesPackage, Model model) {
		model.addAttribute("salesPackage", salesPackage);
		return "modules/sales/salesPackageList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesPackage:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesPackage> listData(SalesPackage salesPackage, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesPackage> page = salesPackageService.findPage(new Page<SalesPackage>(request, response), salesPackage); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesPackage:view")
	@RequestMapping(value = "form")
	public String form(SalesPackage salesPackage, Model model) {
		model.addAttribute("salesPackage", salesPackage);
		return "modules/sales/salesPackageForm";
	}

	/**
	 * 保存优惠套餐配置
	 */
	@RequiresPermissions("sales:salesPackage:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesPackage salesPackage) {
		salesPackageService.save(salesPackage);
		return renderResult(Global.TRUE, "保存优惠套餐配置成功！");
	}
	
	/**
	 * 停用优惠套餐配置
	 */
	@RequiresPermissions("sales:salesPackage:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesPackage salesPackage) {
		salesPackage.setStatus(SalesPackage.STATUS_DISABLE);
		salesPackageService.updateStatus(salesPackage);
		return renderResult(Global.TRUE, "停用优惠套餐配置成功");
	}
	
	/**
	 * 启用优惠套餐配置
	 */
	@RequiresPermissions("sales:salesPackage:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesPackage salesPackage) {
		salesPackage.setStatus(SalesPackage.STATUS_NORMAL);
		salesPackageService.updateStatus(salesPackage);
		return renderResult(Global.TRUE, "启用优惠套餐配置成功");
	}
	
	/**
	 * 删除优惠套餐配置
	 */
	@RequiresPermissions("sales:salesPackage:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesPackage salesPackage) {
		salesPackageService.delete(salesPackage);
		return renderResult(Global.TRUE, "删除优惠套餐配置成功！");
	}
	
}