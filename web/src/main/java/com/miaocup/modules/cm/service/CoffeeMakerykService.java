/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.dao.CoffeeMakerykDao;
import com.miaocup.modules.cm.entity.CoffeeMakerResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 咖啡机Service
 * @author hejun
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly=true)
public class CoffeeMakerykService extends CrudService<CoffeeMakerykDao, CoffeeMakerResult> {

	@Override
	public Page<CoffeeMakerResult> findPage(Page<CoffeeMakerResult> page, CoffeeMakerResult coffeeMakerResult) {
		return super.findPage(page, coffeeMakerResult);
	}
}