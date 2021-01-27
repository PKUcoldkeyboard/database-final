package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.BookFullReduction;
import org.springframework.stereotype.Repository;

/**
 * BookFullReductionDAO继承基类
 */
@Repository
public interface BookFullReductionDAO extends MyBatisBaseDao<BookFullReduction, Long> {
}