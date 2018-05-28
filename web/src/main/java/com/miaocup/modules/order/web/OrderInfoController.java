/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.utils.DictUtils;
import com.miaocup.common.jms.MiaocupProducer;
import com.miaocup.common.utils.ExcelUtils;
import com.miaocup.modules.order.entity.OrderInfo;
import com.miaocup.modules.order.service.OrderInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单管理Controller
 * @author yangkun
 * @version 2018-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderInfo")
public class OrderInfoController extends BaseController {

	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private MiaocupProducer refundProducer;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OrderInfo get(String id, boolean isNewRecord) {
		return orderInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("order:orderInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderInfo orderInfo, Model model) {
		model.addAttribute("orderInfo", orderInfo);
		return "modules/order/orderInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("order:orderInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OrderInfo> listData(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<OrderInfo> page = orderInfoService.findPage(new Page<OrderInfo>(request, response), orderInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("order:orderInfo:view")
	@RequestMapping(value = "form")
	public String form(OrderInfo orderInfo, Model model) {
		model.addAttribute("orderInfo", orderInfo);
		return "modules/order/orderInfoForm";
	}

	/**
	 * 保存订单管理
	 */
	@RequiresPermissions("order:orderInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OrderInfo orderInfo) {
		orderInfoService.save(orderInfo);
		return renderResult(Global.TRUE, "保存订单管理成功！");
	}
	
	/**
	 * 停用订单管理
	 */
	@RequiresPermissions("order:orderInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(OrderInfo orderInfo) {
		orderInfo.setStatus(OrderInfo.STATUS_DISABLE);
		orderInfoService.updateStatus(orderInfo);
		return renderResult(Global.TRUE, "停用订单管理成功");
	}
	
	/**
	 * 启用订单管理
	 */
	@RequiresPermissions("order:orderInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(OrderInfo orderInfo) {
		orderInfo.setStatus(OrderInfo.STATUS_NORMAL);
		orderInfoService.updateStatus(orderInfo);
		return renderResult(Global.TRUE, "启用订单管理成功");
	}
	
	/**
	 * 删除订单管理
	 */
	@RequiresPermissions("order:orderInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OrderInfo orderInfo) {
		orderInfoService.delete(orderInfo);
		return renderResult(Global.TRUE, "删除订单管理成功！");
	}
	/**
	 * 退款
	 */
	@RequiresPermissions("order:orderInfo:edit")
	@RequestMapping(value = "refund")
	@ResponseBody
	public String refund(OrderInfo orderInfo) {
		refundProducer.sendMsg(orderInfo.getId());
		return renderResult(Global.TRUE, "退款发起成功");
	}
	@RequiresPermissions("order:orderInfo:view")
	@RequestMapping(value = "exportFile")
	@ResponseBody
	public String exportFile(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response){
		List<OrderInfo> list = orderInfoService.findList(orderInfo);
		System.out.println("list.size() = " + list.size());
		List<List<Object>> dataSet = new ArrayList<List<Object>>(list.size());
		List<Object> data = null;
		for (OrderInfo order:list) {
			data = new ArrayList<Object>();
			data.add(order.getDealer());
			data.add(order.getCmId());
			data.add(order.getUserId());
			data.add(order.getAmount());
			data.add(order.getPaymentAmount());
			data.add(DictUtils.getDictLabel("pay_method",order.getPayChannel().toString(),""));
			data.add(order.getPayId());
			data.add(order.getDiscountId());
			data.add(order.getSuccessDate());
			dataSet.add(data);
		}
		String[] headers = new String[] {
				"经销商", "咖啡机编号", "用户编号","订单金额",
				"付款总额", "支付渠道", "支付流水号","优惠券编号",
				"支付成功时间"
		};
		ExcelUtils.export("订单信息", "订单信息", headers, dataSet, response);
		return renderResult(Global.TRUE, "数据导出成功");
	}
}