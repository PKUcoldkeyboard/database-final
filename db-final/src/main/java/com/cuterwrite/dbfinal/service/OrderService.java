package com.cuterwrite.dbfinal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.entity.Order;

/**  
 * @author Pang S.Z.
 * @create 2021-01-23 19:39:27 
 */
public interface OrderService {
	/**
	 * 添加订单
	 */
	@Transactional
	int insert(Order order);
	/**
	 * 更新订单
	 */
	@Transactional
	int update(Long id,Order order);
	/**
	 * 订单列表
	 */
	List<Order>list();
	/**
	 * 删除订单
	 */
	@Transactional
	int delete(Long id);
	/**
	 * 查询单个订单
	 */
	Order select(Long id);
	/**
	 * 根据订单编号查询
	 */
	Order selectBySn(String orderId);
}
