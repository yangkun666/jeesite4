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
import com.miaocup.modules.cm.entity.ApplyTry;
import com.miaocup.modules.cm.service.ApplyTryService;

/**
 * 申请试用Controller
 * @author yangkun
 * @version 2018-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/applyTry")
public class ApplyTryController extends BaseController {

	@Autowired
	private ApplyTryService applyTryService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ApplyTry get(String id, boolean isNewRecord) {
		return applyTryService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:applyTry:view")
	@RequestMapping(value = {"list", ""})
	public String list(ApplyTry applyTry, Model model) {
		model.addAttribute("applyTry", applyTry);
		return "modules/cm/applyTryList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:applyTry:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ApplyTry> listData(ApplyTry applyTry, HttpServletRequest request, HttpServletResponse response) {
		Page<ApplyTry> page = applyTryService.findPage(new Page<ApplyTry>(request, response), applyTry); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:applyTry:view")
	@RequestMapping(value = "form")
	public String form(ApplyTry applyTry, Model model) {
		model.addAttribute("applyTry", applyTry);
		return "modules/cm/applyTryForm";
	}

	/**
	 * 保存申请试用
	 */
	@RequiresPermissions("cm:applyTry:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ApplyTry applyTry) {
		applyTryService.save(applyTry);
		return renderResult(Global.TRUE, "保存申请试用成功！");
	}
	
	/**
	 * 删除申请试用
	 */
	@RequiresPermissions("cm:applyTry:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ApplyTry applyTry) {
		applyTryService.delete(applyTry);
		return renderResult(Global.TRUE, "删除申请试用成功！");
	}
	
}