/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.service;

import java.util.List;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.entity.EmpUser;
import com.jeesite.modules.sys.utils.EmpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.user.entity.ClientUser;
import com.miaocup.modules.user.dao.ClientUserDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 会员表Service
 * @author hejun
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly=true)
public class ClientUserService extends CrudService<ClientUserDao, ClientUser> {
	
	/**
	 * 获取单条数据
	 * @param clientUser
	 * @return
	 */
	@Override
	public ClientUser get(ClientUser clientUser) {
		return super.get(clientUser);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param clientUser
	 * @return
	 */
	@Override
	public Page<ClientUser> findPage(Page<ClientUser> page, ClientUser clientUser) {
		return super.findPage(page, clientUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param clientUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ClientUser clientUser) {
		super.save(clientUser);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(clientUser.getId(), "clientUser_image");
	}
	
	/**
	 * 更新状态
	 * @param clientUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ClientUser clientUser) {
		super.updateStatus(clientUser);
	}
	
	/**
	 * 删除数据
	 * @param clientUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ClientUser clientUser) {
		super.delete(clientUser);
	}

}