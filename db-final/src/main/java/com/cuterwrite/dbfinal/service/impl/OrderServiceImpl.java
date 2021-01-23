package com.cuterwrite.dbfinal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.OrderDAO;
import com.cuterwrite.dbfinal.entity.Order;
import com.cuterwrite.dbfinal.service.OrderService;
import com.cuterwrite.dbfinal.service.RedisService;

import cn.hutool.json.JSONUtil;

/**  
 * @author Pang S.Z.
 * @create 2021-01-23 19:42:54 
 */
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDAO dao;
	@Autowired
	private RedisService service;

	@Override
	public int insert(Order order) {
		service.set("order"+order.getId(), JSONUtil.toJsonStr(order));
		return dao.insert(order);
	}

	@Override
	public int update(Long id, Order order) {
		service.set("order"+id, JSONUtil.toJsonStr(order));
		return dao.updateByPrimaryKey(order);
	}

	@Override
	public List<Order> list() {
		return dao.selectAll();
	}

	@Override
	public int delete(Long id) {
		service.remove("order"+id);
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Order select(Long id) {
		//先从缓存拿
		String value=service.get("order"+id);
		Order order=new Order();
		if(value==null) {
			order=dao.selectByPrimaryKey(id);
			service.set("order"+id, JSONUtil.toJsonStr(order));
		}else {
			order=JSONUtil.toBean(service.get("order"+id),Order.class);
		}
		return order;
	}

	@Override
	public Order selectBySn(String orderId) {
		return dao.selectByOrderId(orderId);
	}
	
}
