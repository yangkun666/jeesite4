/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miaocup.common.jms.MiaocupProducer;
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
import com.miaocup.modules.order.entity.ApplyRefund;
import com.miaocup.modules.order.service.ApplyRefundService;

/**
 * 退款申请Controller
 * @author yangkun
 * @version 2018-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/order/applyRefund")
public class ApplyRefundController extends BaseController {

	@Autowired
	private ApplyRefundService applyRefundService;
	@Autowired
	private MiaocupProducer refundProducer;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ApplyRefund get(String id, boolean isNewRecord) {
		return applyRefundService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("order:applyRefund:view")
	@RequestMapping(value = {"list", ""})
	public String list(ApplyRefund applyRefund, Model model) {
		model.addAttribute("applyRefund", applyRefund);
		return "modules/order/applyRefundList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("order:applyRefund:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ApplyRefund> listData(ApplyRefund applyRefund, HttpServletRequest request, HttpServletResponse response) {
		Page<ApplyRefund> page = applyRefundService.findPage(new Page<ApplyRefund>(request, response), applyRefund); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("order:applyRefund:view")
	@RequestMapping(value = "form")
	public String form(ApplyRefund applyRefund, Model model) {
		model.addAttribute("applyRefund", applyRefund);
		return "modules/order/applyRefundForm";
	}

	/**
	 * 保存退款申请
	 */
	@RequiresPermissions("order:applyRefund:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ApplyRefund applyRefund) {
		if(applyRefund.getReason()==null || applyRefund.getReason()== ""){
			applyRefund.setReason("申请退款");
		}
		if(applyRefund.getStatus().equals("1")){
			refundProducer.sendMsg(applyRefund.getOrderId());
			applyRefundService.save(applyRefund);
			applyRefundService.updateStatus(applyRefund);
			return renderResult(Global.TRUE, "退款成功！");
		}
		applyRefundService.save(applyRefund);
		return renderResult(Global.TRUE, "保存退款申请成功！");
	}
	
	/**
	 * 删除退款申请
	 */
	@RequiresPermissions("order:applyRefund:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ApplyRefund applyRefund) {
		applyRefundService.delete(applyRefund);
		return renderResult(Global.TRUE, "删除退款申请成功！");
	}
	
}