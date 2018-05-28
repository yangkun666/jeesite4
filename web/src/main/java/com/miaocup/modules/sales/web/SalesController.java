/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miaocup.modules.cm.entity.CmGrade;
import com.miaocup.modules.cm.entity.Coffee;
import com.miaocup.modules.cm.entity.Place;
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
import com.miaocup.modules.sales.entity.Sales;
import com.miaocup.modules.sales.service.SalesService;

import java.util.List;

/**
 * 限时促销配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/sales")
public class SalesController extends BaseController {

	@Autowired
	private SalesService salesService;
	@Autowired
	private CoffeeService coffeeService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Sales get(String id, boolean isNewRecord) {
		return salesService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:sales:view")
	@RequestMapping(value = {"list", ""})
	public String list(Sales sales, Model model) {
		this.initSelect(model);
		model.addAttribute("sales", sales);
		return "modules/sales/salesList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:sales:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Sales> listData(Sales sales, HttpServletRequest request, HttpServletResponse response) {
		Page<Sales> page = salesService.findPage(new Page<Sales>(request, response), sales); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:sales:view")
	@RequestMapping(value = "form")
	public String form(Sales sales, Model model) {
		this.initSelect(model);
		model.addAttribute("sales", sales);
		return "modules/sales/salesForm";
	}

	/**
	 * 保存限时促销配置
	 */
	@RequiresPermissions("sales:sales:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Sales sales) {
		salesService.save(sales);
		return renderResult(Global.TRUE, "保存限时促销配置成功！");
	}
	
	/**
	 * 停用限时促销配置
	 */
	@RequiresPermissions("sales:sales:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Sales sales) {
		sales.setStatus(Sales.STATUS_DISABLE);
		salesService.updateStatus(sales);
		return renderResult(Global.TRUE, "停用限时促销配置成功");
	}
	
	/**
	 * 启用限时促销配置
	 */
	@RequiresPermissions("sales:sales:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Sales sales) {
		sales.setStatus(Sales.STATUS_NORMAL);
		salesService.updateStatus(sales);
		return renderResult(Global.TRUE, "启用限时促销配置成功");
	}
	
	/**
	 * 删除限时促销配置
	 */
	@RequiresPermissions("sales:sales:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Sales sales) {
		salesService.delete(sales);
		return renderResult(Global.TRUE, "删除限时促销配置成功！");
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