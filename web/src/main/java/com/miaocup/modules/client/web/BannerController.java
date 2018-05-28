/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.client.web;

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
import com.miaocup.modules.client.entity.Banner;
import com.miaocup.modules.client.service.BannerService;

/**
 * 广告管理Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/client/banner")
public class BannerController extends BaseController {

	@Autowired
	private BannerService bannerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Banner get(String id, boolean isNewRecord) {
		return bannerService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("client:banner:view")
	@RequestMapping(value = {"list", ""})
	public String list(Banner banner, Model model) {
		model.addAttribute("banner", banner);
		return "modules/client/bannerList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("client:banner:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Banner> listData(Banner banner, HttpServletRequest request, HttpServletResponse response) {
		Page<Banner> page = bannerService.findPage(new Page<Banner>(request, response), banner); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("client:banner:view")
	@RequestMapping(value = "form")
	public String form(Banner banner, Model model) {
		model.addAttribute("banner", banner);
		return "modules/client/bannerForm";
	}

	/**
	 * 保存广告管理
	 */
	@RequiresPermissions("client:banner:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Banner banner) {
		bannerService.save(banner);
		return renderResult(Global.TRUE, "保存广告管理成功！");
	}
	
	/**
	 * 停用广告管理
	 */
	@RequiresPermissions("client:banner:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Banner banner) {
		banner.setStatus(Banner.STATUS_DISABLE);
		bannerService.updateStatus(banner);
		return renderResult(Global.TRUE, "停用广告管理成功");
	}
	
	/**
	 * 启用广告管理
	 */
	@RequiresPermissions("client:banner:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Banner banner) {
		banner.setStatus(Banner.STATUS_NORMAL);
		bannerService.updateStatus(banner);
		return renderResult(Global.TRUE, "启用广告管理成功");
	}
	
	/**
	 * 删除广告管理
	 */
	@RequiresPermissions("client:banner:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Banner banner) {
		bannerService.delete(banner);
		return renderResult(Global.TRUE, "删除广告管理成功！");
	}
	
}