package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.ConmmntContent;
import org.springframework.stereotype.Repository;

/**
 * ConmmntContentDAO继承基类
 */
@Repository
public interface ConmmntContentDAO extends MyBatisBaseDao<ConmmntContent, Long> {
}