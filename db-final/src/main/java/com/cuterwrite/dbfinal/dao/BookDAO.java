package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * BookDAO继承基类
 */
@Repository
public interface BookDAO extends MyBatisBaseDao<Book, Long> {
}