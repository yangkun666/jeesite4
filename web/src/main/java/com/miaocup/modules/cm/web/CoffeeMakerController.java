/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.UserDataScope;
import com.miaocup.common.jms.MiaocupProducer;
import com.miaocup.modules.cm.entity.*;
import com.miaocup.modules.cm.service.*;
import com.miaocup.modules.coupon.entity.Coupon;
import com.miaocup.modules.coupon.service.CouponService;
import com.miaocup.modules.order.entity.*;
import com.miaocup.modules.order.service.ApplyRefundService;
import com.miaocup.modules.order.service.OrderInfoService;
import com.miaocup.modules.user.entity.ClientUser;
import com.miaocup.modules.user.service.ClientUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 咖啡机Controller
 * @author hejun
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/coffeeMaker")
public class CoffeeMakerController extends BaseController {

	@Autowired
	private CoffeeMakerService coffeeMakerService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private CmGradeService cmGradeService;

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private MiaocupProducer commandProducer;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private ApplyRefundService applyRefundService;
	@Autowired
	private ClientUserService clientUserService;
	@Autowired
	private CoffeeMakerykService coffeeMakerykService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CoffeeMaker get(String id, boolean isNewRecord) {
		return coffeeMakerService.get(new Class<?>[]{String.class},
				new Object[]{id}, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = {"list", ""})
	public String list(CoffeeMaker coffeeMaker, Model model) {
		this.initSelect(model);
		model.addAttribute("coffeeMaker", coffeeMaker);
		return "modules/cm/coffeeMakerList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CoffeeMaker> listData(CoffeeMaker coffeeMaker, HttpServletRequest request, HttpServletResponse response) {
		coffeeMakerService.addDataScopeFilter(coffeeMaker, UserDataScope.CTRL_PERMI_MANAGE);
		Page<CoffeeMaker> page = coffeeMakerService.findPage(new Page<CoffeeMaker>(request, response), coffeeMaker); 
		return page;
	}
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = {"top"})
	public String top(CoffeeMakerResult coffeeMakerResult, Model model) {
		model.addAttribute("coffeeMakerResult", coffeeMakerResult);
		return "modules/cm/coffeeMakerTop";
	}
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "topData")
	@ResponseBody
	public Page<CoffeeMakerykResult> topData(CoffeeMakerResult coffeeMakerResult, HttpServletRequest request, HttpServletResponse response) {
		Page<CoffeeMakerResult> page =coffeeMakerykService.findPage(new Page<CoffeeMakerResult>(request, response), coffeeMakerResult);
		List<CoffeeMakerykResult> coffeeMakerykResultList = Lists.newArrayList();
		page.getList().forEach(coffeeMakerResult1 -> {
			CoffeeMakerykResult coffeeMakerykResult = new CoffeeMakerykResult();
			BeanUtils.copyProperties(coffeeMakerResult1, coffeeMakerykResult);
			coffeeMakerykResultList.add(coffeeMakerykResult);
		});
		Page<CoffeeMakerykResult> coffeeMakerykResultPage = new Page<>();
		coffeeMakerykResultPage.setList(coffeeMakerykResultList);
		coffeeMakerykResultPage.setCount(page.getCount());
		coffeeMakerykResultPage.setPageNo(page.getPageNo());
		coffeeMakerykResultPage.setPageSize(page.getPageSize());
		return coffeeMakerykResultPage;
	}
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "form")
	public String form(CoffeeMaker coffeeMaker, Model model) {
		this.initSelect(model);

		Coffee coffee = new Coffee();
		List<Coffee> coffeeList = coffeeService.findList(coffee);
		model.addAttribute("coffeeList", coffeeList);

		model.addAttribute("coffeeMaker", coffeeMaker);
		return "modules/cm/coffeeMakerForm";
	}

	/**
	 * 保存咖啡机
	 */
	@RequiresPermissions("cm:coffeeMaker:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CoffeeMaker coffeeMaker) {
		coffeeMakerService.save(coffeeMaker);
		return renderResult(Global.TRUE, "保存咖啡机成功！");
	}
	
	/**
	 * 停用咖啡机
	 */
	@RequiresPermissions("cm:coffeeMaker:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CoffeeMaker coffeeMaker) {
		coffeeMaker.setStatus(CoffeeMaker.STATUS_DISABLE);
		coffeeMakerService.updateStatus(coffeeMaker);
		return renderResult(Global.TRUE, "停用咖啡机成功");
	}
	
	/**
	 * 启用咖啡机
	 */
	@RequiresPermissions("cm:coffeeMaker:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CoffeeMaker coffeeMaker) {
		coffeeMaker.setStatus(CoffeeMaker.STATUS_NORMAL);
		coffeeMakerService.updateStatus(coffeeMaker);
		return renderResult(Global.TRUE, "启用咖啡机成功");
	}
	
	/**
	 * 删除咖啡机
	 */
	@RequiresPermissions("cm:coffeeMaker:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CoffeeMaker coffeeMaker) {
		coffeeMakerService.delete(coffeeMaker);
		return renderResult(Global.TRUE, "删除咖啡机成功！");
	}

	@RequiresPermissions("cm:coffeeMaker:edit")
	@RequestMapping(value = "command")
	@ResponseBody
	public String command(String machineId, String machineCode, String command, Integer cups) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("machineId", machineId);
		params.put("machineCode", machineCode);
		params.put("command", command);
		params.put("cups", cups);
		commandProducer.sendMsg(JSON.toJSONString(params));
        return renderResult(Global.TRUE, "指令发送成功");
	}

	/**
	 * 初始化页面下拉框选项值
	 */
	private void initSelect(Model model) {
		Place place = new Place();
		List<Place> placeList = placeService.findList(place);
		model.addAttribute("placeList", placeList);
		Coupon coupon = new Coupon();
		coupon.setStatus("1");
		List<Coupon> couponList =couponService.findList(coupon);
		model.addAttribute("couponList", couponList);
		CmGrade cmGrade = new CmGrade();
		List<CmGrade> gradeList = cmGradeService.findList(cmGrade);
		model.addAttribute("gradeList", gradeList);
	}
	/**
	 * 跳转统计页面
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "statistics")
	public String statistics(OrderInfoCondition orderInfoCondition, Model model) {
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		List<CoffeeMaker> coffeeMakerList = coffeeMakerService.findList(coffeeMaker);
		OrderInfo order = new OrderInfo();
		order.setStatus("1");
		List<OrderInfo> orderInfoList = orderInfoService.findList(order);
		OrderInfo order2 = new OrderInfo();
		order2.setStatus("5");
		List<OrderInfo> orderInfoList2 = orderInfoService.findList(order2);
		Double TKJENum=0.0 ;
		String TKNum = orderInfoList2.size()+"";
		Double LYNum  =0.0;
		String ckNum = orderInfoList.size()+"";
		for (OrderInfo orderInfo: orderInfoList) {
			LYNum += orderInfo.getPaymentAmount();
		}
		for (OrderInfo orderInfo: orderInfoList2) {
			TKJENum += orderInfo.getPaymentAmount();
		}
		model.addAttribute("TKJENum", TKJENum);
		model.addAttribute("TKNum", TKNum);
		model.addAttribute("LYNum", LYNum);
		model.addAttribute("ckNum", ckNum);
		model.addAttribute("orderInfoCondition", orderInfoCondition);
		model.addAttribute("coffeeMakerList", coffeeMakerList);
		return "modules/cm/statistics";
	}

	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "shouye")
	public String shouye(OrderInfoCondition orderInfoCondition, Model model) {
		OrderInfo order = new OrderInfo();
		order.setStatus("1");
		List<OrderInfo> orderInfoList = orderInfoService.findList(order);
		String ckNum = orderInfoList.size()+"";
		String JRckNum = coffeeMakerService.statisticsDay()+"";
		String dqNum = placeService.findList(new Place()).size()+"";
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		List<CoffeeMaker> coffeeMakerList = coffeeMakerService.findList(coffeeMaker);
		String cfjNum = coffeeMakerList.size()+"";
		coffeeMaker.setOperStatus(2);
		coffeeMakerList =  coffeeMakerService.findList(coffeeMaker);
		String yjcfjNum= coffeeMakerList.size()+"";
		String hyNum= clientUserService.findList(new ClientUser()).size()+"";
		String yccfjNum= coffeeMakerList.size()+"";
		String xfhyNum = coffeeMakerService.consumeUserCount()+"";
		model.addAttribute("ckNum", ckNum);
		model.addAttribute("JRckNum", JRckNum);
		model.addAttribute("cfjNum", cfjNum);
		model.addAttribute("yjcfjNum", yjcfjNum);
		model.addAttribute("yccfjNum", yccfjNum);
		model.addAttribute("xfhyNum", xfhyNum);
		model.addAttribute("dqNum", dqNum);
		model.addAttribute("hyNum", hyNum);


		List<MapResult> mapResult = coffeeMakerService.statisticsMap();
		JSONArray json  = new JSONArray();
		for(MapResult map : mapResult){
			System.out.println(map.getCount()+"            :             "+map.getName());
			JSONObject jo = new JSONObject();
			jo.put("value", map.getCount());
			jo.put("name", map.getName());
			json.put(jo);
		}
		model.addAttribute("json", json);
		int wx = 0;
		int zfb = 0;
		int yl = 0;
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setStatus("1");
		orderInfo.setPayChannel(0);
		wx = orderInfoService.findList(orderInfo).size();
		orderInfo.setPayChannel(1);
		zfb = orderInfoService.findList(orderInfo).size();
		orderInfo.setPayChannel(2);
		yl = orderInfoService.findList(orderInfo).size();
		model.addAttribute("yl", yl);
		model.addAttribute("zfb", zfb);
		model.addAttribute("wx", wx);
		ApplyRefund applyRefund = new ApplyRefund();
		applyRefund.setStatus("0");
		OrderInfo info = new OrderInfo();
		model.addAttribute("DDNum", orderInfoService.findList(info).size());
		model.addAttribute("TKNum", applyRefundService.findList(applyRefund).size());
		return "modules/sys/sysDesktop";
	}
	/**
	 * 统计数据
	 */
	@RequiresPermissions("cm:coffeeMaker:view")
	@RequestMapping(value = "statisticsData")
	public List<OrderInfoResult> statisticsData(OrderInfoCondition orderInfoCondition, HttpServletRequest request, HttpServletResponse response, Model model) {
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		List<CoffeeMaker> coffeeMakerList = coffeeMakerService.findList(coffeeMaker);
		OrderInfo order = new OrderInfo();
		if(orderInfoCondition.getCoffeeMakerId()!=null){
			order.setCmId(orderInfoCondition.getCoffeeMakerId());
		}
		OrderInfo order2 = new OrderInfo();
		if(orderInfoCondition.getLastStatisticsTime_gte()!=null ){
			try {
				order.setCreateDate_gte((orderInfoCondition.getLastStatisticsTime_gte()));
				order2.setCreateDate_gte((orderInfoCondition.getLastStatisticsTime_gte()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(orderInfoCondition.getLastStatisticsTime_lte()!=null ){
			try {
				order.setCreateDate_lte((orderInfoCondition.getLastStatisticsTime_lte()));
				order2.setCreateDate_lte((orderInfoCondition.getLastStatisticsTime_lte()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(orderInfoCondition.getCoffeeMakerId()!="" && orderInfoCondition.getCoffeeMakerId()!=null){
			order.setCmId(orderInfoCondition.getCoffeeMakerId());
			order2.setCmId(orderInfoCondition.getCoffeeMakerId());
		}
		order.setStatus("1");
		List<OrderInfo> orderInfoList = orderInfoService.findList(order);
		order2.setStatus("5");
		List<OrderInfo> orderInfoList2 = orderInfoService.findList(order2);
		Double TKJENum=0.0 ;
		String TKNum = orderInfoList2.size()+"";
		Double LYNum  =0.0;
		String ckNum = orderInfoList.size()+"";
		for (OrderInfo orderInfo: orderInfoList) {
			LYNum += orderInfo.getPaymentAmount();
		}
		for (OrderInfo orderInfo: orderInfoList2) {
			TKJENum += orderInfo.getPaymentAmount();
		}
		model.addAttribute("TKJENum", TKJENum);
		model.addAttribute("TKNum", TKNum);
		model.addAttribute("LYNum", LYNum);
		model.addAttribute("ckNum", ckNum);
		model.addAttribute("orderInfoCondition", orderInfoCondition);
		model.addAttribute("coffeeMakerList", coffeeMakerList);
		return coffeeMakerService.statisticsData(orderInfoCondition);
	}
}