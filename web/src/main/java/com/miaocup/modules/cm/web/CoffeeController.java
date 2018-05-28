/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.web;

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
import com.miaocup.modules.cm.entity.Coffee;
import com.miaocup.modules.cm.service.CoffeeService;

/**
 * 咖啡品种Controller
 * @author hejun
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/coffee")
public class CoffeeController extends BaseController {

	@Autowired
	private CoffeeService coffeeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Coffee get(String id, boolean isNewRecord) {
		return coffeeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:coffee:view")
	@RequestMapping(value = {"list", ""})
	public String list(Coffee coffee, Model model) {
		model.addAttribute("coffee", coffee);
		return "modules/cm/coffeeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:coffee:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Coffee> listData(Coffee coffee, HttpServletRequest request, HttpServletResponse response) {
		Page<Coffee> page = coffeeService.findPage(new Page<Coffee>(request, response), coffee); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:coffee:view")
	@RequestMapping(value = "form")
	public String form(Coffee coffee, Model model) {
		model.addAttribute("coffee", coffee);
		return "modules/cm/coffeeForm";
	}

	/**
	 * 保存咖啡品种
	 */
	@RequiresPermissions("cm:coffee:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Coffee coffee) {
		coffeeService.save(coffee);
		return renderResult(Global.TRUE, "保存咖啡品种成功！");
	}
	
	/**
	 * 删除咖啡品种
	 */
	@RequiresPermissions("cm:coffee:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Coffee coffee) {
		coffeeService.delete(coffee);
		return renderResult(Global.TRUE, "删除咖啡品种成功！");
	}
	
}