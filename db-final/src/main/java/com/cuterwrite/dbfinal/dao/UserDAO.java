package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Integer> {
	User findByUsername(String username);
	
	int insertUserRole(User userToAdd);
	
	int update(User user);
	
	int updateUserRole(User user);
}