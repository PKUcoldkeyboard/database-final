package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.UserEmail;
import org.springframework.stereotype.Repository;

/**
 * UserEmailDAO继承基类
 */
@Repository
public interface UserEmailDAO extends MyBatisBaseDao<UserEmail, Long> {
    String selectByUsername(String username);
}