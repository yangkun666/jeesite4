/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.CmGrade;
import com.miaocup.modules.cm.dao.CmGradeDao;

/**
 * 咖啡机等级配置Service
 * @author yangkun
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly=true)
public class CmGradeService extends CrudService<CmGradeDao, CmGrade> {
	
	/**
	 * 获取单条数据
	 * @param cmGrade
	 * @return
	 */
	@Override
	public CmGrade get(CmGrade cmGrade) {
		return super.get(cmGrade);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param cmGrade
	 * @return
	 */
	@Override
	public Page<CmGrade> findPage(Page<CmGrade> page, CmGrade cmGrade) {
		return super.findPage(page, cmGrade);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param cmGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CmGrade cmGrade) {
		super.save(cmGrade);
	}
	
	/**
	 * 更新状态
	 * @param cmGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CmGrade cmGrade) {
		super.updateStatus(cmGrade);
	}
	
	/**
	 * 删除数据
	 * @param cmGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CmGrade cmGrade) {
		super.delete(cmGrade);
	}
	
}