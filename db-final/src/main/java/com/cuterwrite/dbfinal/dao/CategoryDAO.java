package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * CategoryDAO继承基类
 */
@Repository
public interface CategoryDAO extends MyBatisBaseDao<Category, Long> {
}