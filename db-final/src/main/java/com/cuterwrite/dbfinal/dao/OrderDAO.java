package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * OrderDAO继承基类
 */
@Repository
public interface OrderDAO extends MyBatisBaseDao<Order, Long> {
}