package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Order;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * OrderDAO继承基类
 */
@Repository
public interface OrderDAO extends MyBatisBaseDao<Order, Long> {
    List<Order> selectAll();

    Order selectByOrderId(String orderId);

    Long selectLast();
}