package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.BookDiscount;
import com.cuterwrite.dbfinal.entity.DiscountedBook;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * BookDiscountDAO继承基类
 */
@Repository
public interface BookDiscountDAO extends MyBatisBaseDao<BookDiscount, Long> {
    List<DiscountedBook> selectAll();
}