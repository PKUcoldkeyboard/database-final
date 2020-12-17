package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.BookLadder;
import org.springframework.stereotype.Repository;

/**
 * BookLadderDAO继承基类
 */
@Repository
public interface BookLadderDAO extends MyBatisBaseDao<BookLadder, Long> {
}