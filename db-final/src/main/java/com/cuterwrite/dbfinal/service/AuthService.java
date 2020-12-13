package com.cuterwrite.dbfinal.service;

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.dto.ChangePwdParam;
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
	@Transactional
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
	/**
	 * 
	 * @Description 修改密码
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return 修改密码后的user
	 */
	User changePwd(ChangePwdParam changePwdParam);
}
