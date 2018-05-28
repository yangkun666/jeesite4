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
import com.miaocup.modules.repair.entity.Opinion;
import com.miaocup.modules.repair.service.OpinionService;

/**
 * 投诉建议Controller
 * @author yangkun
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/repair/opinion")
public class OpinionController extends BaseController {

	@Autowired
	private OpinionService opinionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Opinion get(String id, boolean isNewRecord) {
		return opinionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("repair:opinion:view")
	@RequestMapping(value = {"list", ""})
	public String list(Opinion opinion, Model model) {
		model.addAttribute("opinion", opinion);
		return "modules/repair/opinionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("repair:opinion:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Opinion> listData(Opinion opinion, HttpServletRequest request, HttpServletResponse response) {
		Page<Opinion> page = opinionService.findPage(new Page<Opinion>(request, response), opinion); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("repair:opinion:view")
	@RequestMapping(value = "form")
	public String form(Opinion opinion, Model model) {
		model.addAttribute("opinion", opinion);
		return "modules/repair/opinionForm";
	}

	/**
	 * 保存投诉建议
	 */
	@RequiresPermissions("repair:opinion:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Opinion opinion) {
		opinionService.save(opinion);
		return renderResult(Global.TRUE, "保存投诉建议成功！");
	}
	
	/**
	 * 停用投诉建议
	 */
	@RequiresPermissions("repair:opinion:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Opinion opinion) {
		opinion.setStatus(Opinion.STATUS_DISABLE);
		opinionService.updateStatus(opinion);
		return renderResult(Global.TRUE, "停用投诉建议成功");
	}
	
	/**
	 * 启用投诉建议
	 */
	@RequiresPermissions("repair:opinion:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Opinion opinion) {
		opinion.setStatus(Opinion.STATUS_NORMAL);
		opinionService.updateStatus(opinion);
		return renderResult(Global.TRUE, "启用投诉建议成功");
	}
	
	/**
	 * 删除投诉建议
	 */
	@RequiresPermissions("repair:opinion:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Opinion opinion) {
		opinionService.delete(opinion);
		return renderResult(Global.TRUE, "删除投诉建议成功！");
	}
	
}