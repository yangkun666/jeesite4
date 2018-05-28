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
import com.miaocup.modules.sales.entity.SalesRaise;
import com.miaocup.modules.sales.service.SalesRaiseService;

import java.util.List;

/**
 * 加价购管理Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesRaise")
public class SalesRaiseController extends BaseController {

	@Autowired
	private SalesRaiseService salesRaiseService;

	@Autowired
	private CoffeeService coffeeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SalesRaise get(String id, boolean isNewRecord) {
		return salesRaiseService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:salesRaise:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesRaise salesRaise, Model model) {
		this.initSelect(model);
		model.addAttribute("salesRaise", salesRaise);
		return "modules/sales/salesRaiseList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:salesRaise:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SalesRaise> listData(SalesRaise salesRaise, HttpServletRequest request, HttpServletResponse response) {
		Page<SalesRaise> page = salesRaiseService.findPage(new Page<SalesRaise>(request, response), salesRaise); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:salesRaise:view")
	@RequestMapping(value = "form")
	public String form(SalesRaise salesRaise, Model model) {
		this.initSelect(model);
		model.addAttribute("salesRaise", salesRaise);
		return "modules/sales/salesRaiseForm";
	}

	/**
	 * 保存加价购管理
	 */
	@RequiresPermissions("sales:salesRaise:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SalesRaise salesRaise) {
		salesRaiseService.save(salesRaise);
		return renderResult(Global.TRUE, "保存加价购管理成功！");
	}
	
	/**
	 * 停用加价购管理
	 */
	@RequiresPermissions("sales:salesRaise:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SalesRaise salesRaise) {
		salesRaise.setStatus(SalesRaise.STATUS_DISABLE);
		salesRaiseService.updateStatus(salesRaise);
		return renderResult(Global.TRUE, "停用加价购管理成功");
	}
	
	/**
	 * 启用加价购管理
	 */
	@RequiresPermissions("sales:salesRaise:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SalesRaise salesRaise) {
		salesRaise.setStatus(SalesRaise.STATUS_NORMAL);
		salesRaiseService.updateStatus(salesRaise);
		return renderResult(Global.TRUE, "启用加价购管理成功");
	}
	
	/**
	 * 删除加价购管理
	 */
	@RequiresPermissions("sales:salesRaise:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SalesRaise salesRaise) {
		salesRaiseService.delete(salesRaise);
		return renderResult(Global.TRUE, "删除加价购管理成功！");
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