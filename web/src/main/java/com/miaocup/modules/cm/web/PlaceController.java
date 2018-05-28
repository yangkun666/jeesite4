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
import com.miaocup.modules.cm.entity.Place;
import com.miaocup.modules.cm.service.PlaceService;

/**
 * 场地管理Controller
 * @author hejun
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/place")
public class PlaceController extends BaseController {

	@Autowired
	private PlaceService placeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Place get(String id, boolean isNewRecord) {
		return placeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cm:place:view")
	@RequestMapping(value = {"list", ""})
	public String list(Place place, Model model) {
		model.addAttribute("place", place);
		return "modules/cm/placeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cm:place:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Place> listData(Place place, HttpServletRequest request, HttpServletResponse response) {
		Page<Place> page = placeService.findPage(new Page<Place>(request, response), place); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cm:place:view")
	@RequestMapping(value = "form")
	public String form(Place place, Model model) {
		model.addAttribute("place", place);
		return "modules/cm/placeForm";
	}

	/**
	 * 保存场地
	 */
	@RequiresPermissions("cm:place:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Place place) {
		placeService.save(place);
		return renderResult(Global.TRUE, "保存场地成功！");
	}
	
	/**
	 * 删除场地
	 */
	@RequiresPermissions("cm:place:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Place place) {
		placeService.delete(place);
		return renderResult(Global.TRUE, "删除场地成功！");
	}
	
}