/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.client.entity.DefaultImage;
import com.miaocup.modules.client.dao.DefaultImageDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 默认图片配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class DefaultImageService extends CrudService<DefaultImageDao, DefaultImage> {
	
	/**
	 * 获取单条数据
	 * @param defaultImage
	 * @return
	 */
	@Override
	public DefaultImage get(DefaultImage defaultImage) {
		return super.get(defaultImage);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param defaultImage
	 * @return
	 */
	@Override
	public Page<DefaultImage> findPage(Page<DefaultImage> page, DefaultImage defaultImage) {
		return super.findPage(page, defaultImage);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param defaultImage
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DefaultImage defaultImage) {
		super.save(defaultImage);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(defaultImage.getId(), "defaultImage_image");
	}
	
	/**
	 * 更新状态
	 * @param defaultImage
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DefaultImage defaultImage) {
		super.updateStatus(defaultImage);
	}
	
	/**
	 * 删除数据
	 * @param defaultImage
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DefaultImage defaultImage) {
		super.delete(defaultImage);
	}
	
}