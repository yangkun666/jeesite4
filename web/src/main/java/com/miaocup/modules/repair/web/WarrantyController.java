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
import com.miaocup.modules.repair.entity.Warranty;
import com.miaocup.modules.repair.service.WarrantyService;

/**
 * 保修有礼Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/repair/warranty")
public class WarrantyController extends BaseController {

	@Autowired
	private WarrantyService warrantyService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Warranty get(String id, boolean isNewRecord) {
		return warrantyService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("repair:warranty:view")
	@RequestMapping(value = {"list", ""})
	public String list(Warranty warranty, Model model) {
		model.addAttribute("warranty", warranty);
		return "modules/repair/warrantyList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("repair:warranty:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Warranty> listData(Warranty warranty, HttpServletRequest request, HttpServletResponse response) {
		Page<Warranty> page = warrantyService.findPage(new Page<Warranty>(request, response), warranty); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("repair:warranty:view")
	@RequestMapping(value = "form")
	public String form(Warranty warranty, Model model) {
		model.addAttribute("warranty", warranty);
		return "modules/repair/warrantyForm";
	}

	/**
	 * 保存保修有礼
	 */
	@RequiresPermissions("repair:warranty:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Warranty warranty) {
		warrantyService.save(warranty);
		return renderResult(Global.TRUE, "保存保修有礼成功！");
	}
	
	/**
	 * 停用保修有礼
	 */
	@RequiresPermissions("repair:warranty:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Warranty warranty) {
		warranty.setStatus(Warranty.STATUS_DISABLE);
		warrantyService.updateStatus(warranty);
		return renderResult(Global.TRUE, "停用保修有礼成功");
	}
	
	/**
	 * 启用保修有礼
	 */
	@RequiresPermissions("repair:warranty:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Warranty warranty) {
		warranty.setStatus(Warranty.STATUS_NORMAL);
		warrantyService.updateStatus(warranty);
		return renderResult(Global.TRUE, "启用保修有礼成功");
	}
	
	/**
	 * 删除保修有礼
	 */
	@RequiresPermissions("repair:warranty:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Warranty warranty) {
		warrantyService.delete(warranty);
		return renderResult(Global.TRUE, "删除保修有礼成功！");
	}
	
}