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
import com.miaocup.modules.client.entity.DefaultImage;
import com.miaocup.modules.client.service.DefaultImageService;

/**
 * 默认图片配置Controller
 * @author yangkun
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/client/defaultImage")
public class DefaultImageController extends BaseController {

	@Autowired
	private DefaultImageService defaultImageService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DefaultImage get(String id, boolean isNewRecord) {
		return defaultImageService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("client:defaultImage:view")
	@RequestMapping(value = {"list", ""})
	public String list(DefaultImage defaultImage, Model model) {
		model.addAttribute("defaultImage", defaultImage);
		return "modules/client/defaultImageList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("client:defaultImage:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DefaultImage> listData(DefaultImage defaultImage, HttpServletRequest request, HttpServletResponse response) {
		Page<DefaultImage> page = defaultImageService.findPage(new Page<DefaultImage>(request, response), defaultImage); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("client:defaultImage:view")
	@RequestMapping(value = "form")
	public String form(DefaultImage defaultImage, Model model) {
		model.addAttribute("defaultImage", defaultImage);
		return "modules/client/defaultImageForm";
	}

	/**
	 * 保存默认图片配置
	 */
	@RequiresPermissions("client:defaultImage:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DefaultImage defaultImage) {
		defaultImageService.save(defaultImage);
		return renderResult(Global.TRUE, "保存默认图片配置成功！");
	}
	
	/**
	 * 停用默认图片配置
	 */
	@RequiresPermissions("client:defaultImage:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(DefaultImage defaultImage) {
		defaultImage.setStatus(DefaultImage.STATUS_DISABLE);
		defaultImageService.updateStatus(defaultImage);
		return renderResult(Global.TRUE, "停用默认图片配置成功");
	}
	
	/**
	 * 启用默认图片配置
	 */
	@RequiresPermissions("client:defaultImage:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(DefaultImage defaultImage) {
		defaultImage.setStatus(DefaultImage.STATUS_NORMAL);
		defaultImageService.updateStatus(defaultImage);
		return renderResult(Global.TRUE, "启用默认图片配置成功");
	}
	
	/**
	 * 删除默认图片配置
	 */
	@RequiresPermissions("client:defaultImage:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DefaultImage defaultImage) {
		defaultImageService.delete(defaultImage);
		return renderResult(Global.TRUE, "删除默认图片配置成功！");
	}
	
}