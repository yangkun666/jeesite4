/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miaocup.modules.cm.entity.Coffee;
import com.miaocup.modules.cm.service.CoffeeService;
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
import com.miaocup.modules.sales.entity.SalesPresent;
import com.miaocup.modules.sales.service.SalesPresentService;

import java.util.List;

/**
 * 满即送配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesPresent")
public class SalesPresentController extends BaseController {

	@Autowired
	private SalesPresentService salesPresentService;
	@Autowired
	private CoffeeService coffeeService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesPresent get(String id, boolean isNewRecord) {
		return salesPresentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesPresent:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesPresent salesPresent, Model model) {
		this.initSelect(model);
		model.addAttribute("salesPresent", salesPresent);
		return "modules/sales/salesPresentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesPresent:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesPresent> listData(SalesPresent salesPresent, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesPresent> page = salesPresentService.findPage(new Page<SalesPresent>(request, response), salesPresent); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesPresent:view")
	@RequestMapping(value = "form")
	public String form(SalesPresent salesPresent, Model model) {
		this.initSelect(model);
		model.addAttribute("salesPresent", salesPresent);
		return "modules/sales/salesPresentForm";
	}

	/**
	 * 保存满即送配置
	 */
	@RequiresPermissions("sales:salesPresent:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesPresent salesPresent) {
		salesPresentService.save(salesPresent);
		return renderResult(Global.TRUE, "保存满即送配置成功！");
	}
	
	/**
	 * 停用满即送配置
	 */
	@RequiresPermissions("sales:salesPresent:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesPresent salesPresent) {
		salesPresent.setStatus(SalesPresent.STATUS_DISABLE);
		salesPresentService.updateStatus(salesPresent);
		return renderResult(Global.TRUE, "停用满即送配置成功");
	}
	
	/**
	 * 启用满即送配置
	 */
	@RequiresPermissions("sales:salesPresent:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesPresent salesPresent) {
		salesPresent.setStatus(SalesPresent.STATUS_NORMAL);
		salesPresentService.updateStatus(salesPresent);
		return renderResult(Global.TRUE, "启用满即送配置成功");
	}
	
	/**
	 * 删除满即送配置
	 */
	@RequiresPermissions("sales:salesPresent:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesPresent salesPresent) {
		salesPresentService.delete(salesPresent);
		return renderResult(Global.TRUE, "删除满即送配置成功！");
	}
	/**
	 * 初始化页面下拉框选项值
	 */
	private void initSelect(Model model) {
		Coffee coffee = new Coffee();
		List<Coffee> coffeeList = coffeeService.findList(coffee);
		model.addAttribute("coffeeList", coffeeList);
	}
}