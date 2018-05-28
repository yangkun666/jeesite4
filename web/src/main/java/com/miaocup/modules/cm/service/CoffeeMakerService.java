/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.cm.service;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.entity.EmpUser;
import com.jeesite.modules.sys.utils.EmpUtils;
import com.miaocup.common.cache.redis.RedisClientUtil;
import com.miaocup.common.contants.MiaocupContants;
import com.miaocup.modules.cm.entity.CoffeeMap;
import com.miaocup.modules.cm.entity.Place;
import com.miaocup.modules.order.dao.OrderInfoDao;
import com.miaocup.modules.order.entity.MapResult;
import com.miaocup.modules.order.entity.OrderInfoCondition;
import com.miaocup.modules.order.entity.OrderInfoResult;
import com.miaocup.modules.user.dao.ClientUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.cm.entity.CoffeeMaker;
import com.miaocup.modules.cm.dao.CoffeeMakerDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.miaocup.modules.cm.dao.CoffeeMapDao;

import java.util.List;
import java.util.Objects;

/**
 * 咖啡机Service
 * @author hejun
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly=true)
public class CoffeeMakerService extends CrudService<CoffeeMakerDao, CoffeeMaker> {
	
	@Autowired
	private CoffeeMapDao coffeeDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private RedisClientUtil redisClientUtil;
	@Autowired
	private ClientUserDao clientUserDao;
	@Autowired
	private PlaceService placeService;
	/**
	 * 获取单条数据
	 * @param coffeeMaker
	 * @return
	 */
	@Override
	public CoffeeMaker get(CoffeeMaker coffeeMaker) {
		CoffeeMaker entity = super.get(coffeeMaker);
		if (entity != null){
			CoffeeMap coffee = new CoffeeMap(entity.getId());
			coffee.setStatus(CoffeeMap.STATUS_NORMAL);
			entity.setCoffeeMapList(coffeeDao.findList(coffee));
		}
		return entity;
	}

	/**
	 * 添加数据权限过滤条件
	 * @param ctrlPermi 控制权限类型（拥有的数据权限：DataScope.CTRL_PERMI_HAVE、可管理的数据权限：DataScope.CTRL_PERMI_HAVE）
	 */
	@Override
	public void addDataScopeFilter(CoffeeMaker coffeeMaker, String ctrlPermi) {
		coffeeMaker.getSqlMap().getDataScope().addFilter("dsfOffice",
				"Office", "e.office_code", "a.create_by", ctrlPermi);
		if (StringUtils.isNotBlank(EmpUtils.getCompany().getCompanyCode())){
			coffeeMaker.getSqlMap().getDataScope().addFilter("dsfCompany",
					"Company", "e.company_code", "a.create_by", ctrlPermi);
		}
	}

	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param coffeeMaker
	 * @return
	 */
	@Override
	public Page<CoffeeMaker> findPage(Page<CoffeeMaker> page, CoffeeMaker coffeeMaker) {
		Page<CoffeeMaker> resultPage = super.findPage(page, coffeeMaker);
		for (CoffeeMaker cm : resultPage.getList()) {
			String status = redisClientUtil.get(MiaocupContants.CM_RUN_STATUS_KEY + cm.getMachineId());
			if (StringUtils.isNotBlank(status)) {
				cm.setRunStatus(Integer.valueOf(status));
			}
			String cupsCount = redisClientUtil.get(MiaocupContants.CM_CUPS_COUNT_KEY + coffeeMaker.getMachineId());
			if (StringUtils.isNotBlank(cupsCount)) {
				cm.setCupLimit(cm.getCupLimit() - Integer.valueOf(cupsCount));
			}
		}
		return resultPage;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param coffeeMaker
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CoffeeMaker coffeeMaker) {
		Place place =  placeService.get(coffeeMaker.getPlaceId());
		coffeeMaker.setDealer(place.getDealer());
		super.save(coffeeMaker);
		if (Objects.equals(coffeeMaker.getStatus(), CoffeeMaker.STATUS_NORMAL)) {
			redisClientUtil.hmSet(MiaocupContants.CM_STARTUP_IDS_KEY, coffeeMaker.getMachineId(), coffeeMaker.getCode());
		} else {
			redisClientUtil.hmDel(MiaocupContants.CM_STARTUP_IDS_KEY, coffeeMaker.getMachineId());
		}
		// 保存上传图片
		FileUploadUtils.saveFileUpload(coffeeMaker.getId(), "qrCode");
		// 保存 CoffeeMaker子表
		for (CoffeeMap coffeeMap : coffeeMaker.getCoffeeMapList()){
			if (!CoffeeMap.STATUS_DELETE.equals(coffeeMap.getStatus())){
				coffeeMap.setCmId(coffeeMaker.getMachineId());
				if (coffeeMap.getIsNewRecord()){
					coffeeMap.preInsert();
					coffeeDao.insert(coffeeMap);
				}else{
					coffeeMap.preUpdate();
					coffeeDao.update(coffeeMap);
				}
			}else{
				coffeeDao.delete(coffeeMap);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param coffeeMaker
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CoffeeMaker coffeeMaker) {
		super.updateStatus(coffeeMaker);
		coffeeMaker = super.get(coffeeMaker);
		if (Objects.equals(coffeeMaker.getStatus(), CoffeeMaker.STATUS_NORMAL)) {
			redisClientUtil.hmSet(MiaocupContants.CM_STARTUP_IDS_KEY, coffeeMaker.getMachineId(), coffeeMaker.getCode());
		} else {
			redisClientUtil.hmDel(MiaocupContants.CM_STARTUP_IDS_KEY, coffeeMaker.getMachineId());
		}
	}
	
	/**
	 * 删除数据
	 * @param coffeeMaker
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CoffeeMaker coffeeMaker) {
		super.delete(coffeeMaker);
		CoffeeMap coffeeMap = new CoffeeMap();
		coffeeMap.setCmId(coffeeMaker.getId());
		coffeeDao.delete(coffeeMap);
	}

	public List<OrderInfoResult> statisticsData(OrderInfoCondition orderInfoCondition){
		return orderInfoDao.statisticsData(orderInfoCondition);
	}
	public int statisticsDay(){
		return orderInfoDao.statisticsDay();
	}
	public int consumeUserCount(){
		return  clientUserDao.findConsumeUser().size();
	}
	public int statisticsMonth(){
		return  orderInfoDao.statisticsMonth();
	}
	public List<MapResult> statisticsMap(){
		return  orderInfoDao.statisticsMap();
	}

}