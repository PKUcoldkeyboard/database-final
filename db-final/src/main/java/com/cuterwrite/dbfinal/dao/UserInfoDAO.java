package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * UserInfoDAO继承基类
 */
@Repository
public interface UserInfoDAO extends MyBatisBaseDao<UserInfo, Long> {
}