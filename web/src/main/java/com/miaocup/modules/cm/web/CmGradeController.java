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
import com.miaocup.modules.cm.entity.CmGrade;
import com.miaocup.modules.cm.service.CmGradeService;

/**
 * 咖啡机等级配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/cmGrade")
public class CmGradeController extends BaseController {

	@Autowired
	private CmGradeService cmGradeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CmGrade get(String id, boolean isNewRecord) {
		return cmGradeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:cmGrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmGrade cmGrade, Model model) {
		model.addAttribute("cmGrade", cmGrade);
		return "modules/cm/cmGradeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:cmGrade:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CmGrade> listData(CmGrade cmGrade, HttpServletRequest request, HttpServletResponse response) {
		Page<CmGrade> page = cmGradeService.findPage(new Page<CmGrade>(request, response), cmGrade); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:cmGrade:view")
	@RequestMapping(value = "form")
	public String form(CmGrade cmGrade, Model model) {
		model.addAttribute("cmGrade", cmGrade);
		return "modules/cm/cmGradeForm";
	}

	/**
	 * 保存咖啡机等级配置
	 */
	@RequiresPermissions("cm:cmGrade:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CmGrade cmGrade) {
		cmGradeService.save(cmGrade);
		return renderResult(Global.TRUE, "保存咖啡机等级配置成功！");
	}
	
	/**
	 * 停用咖啡机等级配置
	 */
	@RequiresPermissions("cm:cmGrade:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CmGrade cmGrade) {
		cmGrade.setStatus(CmGrade.STATUS_DISABLE);
		cmGradeService.updateStatus(cmGrade);
		return renderResult(Global.TRUE, "停用咖啡机等级配置成功");
	}
	
	/**
	 * 启用咖啡机等级配置
	 */
	@RequiresPermissions("cm:cmGrade:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CmGrade cmGrade) {
		cmGrade.setStatus(CmGrade.STATUS_NORMAL);
		cmGradeService.updateStatus(cmGrade);
		return renderResult(Global.TRUE, "启用咖啡机等级配置成功");
	}
	
	/**
	 * 删除咖啡机等级配置
	 */
	@RequiresPermissions("cm:cmGrade:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CmGrade cmGrade) {
		cmGradeService.delete(cmGrade);
		return renderResult(Global.TRUE, "删除咖啡机等级配置成功！");
	}
	
}