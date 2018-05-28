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
import com.miaocup.modules.repair.entity.Announcement;
import com.miaocup.modules.repair.service.AnnouncementService;

/**
 * 公告维护管理Controller
 * @author yangkun
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/repair/announcement")
public class AnnouncementController extends BaseController {

	@Autowired
	private AnnouncementService announcementService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Announcement get(String id, boolean isNewRecord) {
		return announcementService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("repair:announcement:view")
	@RequestMapping(value = {"list", ""})
	public String list(Announcement announcement, Model model) {
		model.addAttribute("announcement", announcement);
		return "modules/repair/announcementList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("repair:announcement:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Announcement> listData(Announcement announcement, HttpServletRequest request, HttpServletResponse response) {
		Page<Announcement> page = announcementService.findPage(new Page<Announcement>(request, response), announcement); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("repair:announcement:view")
	@RequestMapping(value = "form")
	public String form(Announcement announcement, Model model) {
		model.addAttribute("announcement", announcement);
		return "modules/repair/announcementForm";
	}

	/**
	 * 保存公告维护管理
	 */
	@RequiresPermissions("repair:announcement:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Announcement announcement) {
		announcementService.save(announcement);
		return renderResult(Global.TRUE, "保存公告维护管理成功！");
	}
	
	/**
	 * 停用公告维护管理
	 */
	@RequiresPermissions("repair:announcement:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Announcement announcement) {
		announcement.setStatus(Announcement.STATUS_DISABLE);
		announcementService.updateStatus(announcement);
		return renderResult(Global.TRUE, "停用公告维护管理成功");
	}
	
	/**
	 * 启用公告维护管理
	 */
	@RequiresPermissions("repair:announcement:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Announcement announcement) {
		announcement.setStatus(Announcement.STATUS_NORMAL);
		announcementService.updateStatus(announcement);
		return renderResult(Global.TRUE, "启用公告维护管理成功");
	}
	
	/**
	 * 删除公告维护管理
	 */
	@RequiresPermissions("repair:announcement:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Announcement announcement) {
		announcementService.delete(announcement);
		return renderResult(Global.TRUE, "删除公告维护管理成功！");
	}
	
}