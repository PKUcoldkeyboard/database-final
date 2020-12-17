package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.UserStatisticsInfo;
import org.springframework.stereotype.Repository;

/**
 * UserStatisticsInfoDAO继承基类
 */
@Repository
public interface UserStatisticsInfoDAO extends MyBatisBaseDao<UserStatisticsInfo, Long> {
}