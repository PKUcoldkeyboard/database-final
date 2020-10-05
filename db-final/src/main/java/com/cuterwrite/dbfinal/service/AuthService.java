package com.cuterwrite.dbfinal.service;

import com.cuterwrite.dbfinal.entity.User;

/**
 * 
 * @author Pang S.Z.
 * @create 2020-09-27 17:13:20
 */
public interface AuthService {
	
	/**
	 * 
	 * @Description 注册
	 * @param userToAdd
	 * @return User
	 */
	User register(User userToAdd);
	/**
	 * 
	 * @Description 登录
	 * @param username
	 * @param password
	 * @return Json Web Token
	 */
	String login(String username,String password);
	/**
	 * 
	 * @Description 刷新token
	 * @param token
	 * @return 新的token
	 */
	String refreshToken(String token);
}
