package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleDAO继承基类
 */
@Repository
public interface RoleDAO extends MyBatisBaseDao<Role, Integer> {
}