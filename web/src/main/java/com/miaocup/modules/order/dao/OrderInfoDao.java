/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.order.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.miaocup.modules.order.entity.MapResult;
import com.miaocup.modules.order.entity.OrderInfo;
import com.miaocup.modules.order.entity.OrderInfoCondition;
import com.miaocup.modules.order.entity.OrderInfoResult;

import java.util.List;

/**
 * 订单管理DAO接口
 * @author yangkun
 * @version 2018-04-16
 */
@MyBatisDao
public interface OrderInfoDao extends CrudDao<OrderInfo> {
	List<OrderInfoResult> statisticsData(OrderInfoCondition orderInfoCondition);
	int statisticsDay();
	int statisticsMonth();
	List<MapResult>  statisticsMap();
}