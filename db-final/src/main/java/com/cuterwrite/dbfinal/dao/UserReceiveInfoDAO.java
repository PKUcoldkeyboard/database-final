package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.UserReceiveInfo;
import org.springframework.stereotype.Repository;

/**
 * UserReceiveInfoDAO继承基类
 */
@Repository
public interface UserReceiveInfoDAO extends MyBatisBaseDao<UserReceiveInfo, Long> {
    UserReceiveInfo selectByUserId(Long userId);
}