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
import com.miaocup.modules.cm.entity.CashDeposit;
import com.miaocup.modules.cm.service.CashDepositService;

/**
 * 咖啡机保证金Controller
 * @author hejun
 * @version 2018-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/cashDeposit")
public class CashDepositController extends BaseController {

	@Autowired
	private CashDepositService cashDepositService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CashDeposit get(String id, boolean isNewRecord) {
		return cashDepositService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:cashDeposit:view")
	@RequestMapping(value = {"list", ""})
	public String list(CashDeposit cashDeposit, Model model) {
		model.addAttribute("cashDeposit", cashDeposit);
		return "modules/cm/cashDepositList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:cashDeposit:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CashDeposit> listData(CashDeposit cashDeposit, HttpServletRequest request, HttpServletResponse response) {
		Page<CashDeposit> page = cashDepositService.findPage(new Page<CashDeposit>(request, response), cashDeposit); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:cashDeposit:view")
	@RequestMapping(value = "form")
	public String form(CashDeposit cashDeposit, Model model) {
		model.addAttribute("cashDeposit", cashDeposit);
		return "modules/cm/cashDepositForm";
	}

	/**
	 * 保存保证金
	 */
	@RequiresPermissions("cm:cashDeposit:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CashDeposit cashDeposit) {
		cashDepositService.save(cashDeposit);
		return renderResult(Global.TRUE, "保存保证金成功！");
	}
	
	/**
	 * 删除保证金
	 */
	@RequiresPermissions("cm:cashDeposit:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CashDeposit cashDeposit) {
		cashDepositService.delete(cashDeposit);
		return renderResult(Global.TRUE, "删除保证金成功！");
	}
	
}