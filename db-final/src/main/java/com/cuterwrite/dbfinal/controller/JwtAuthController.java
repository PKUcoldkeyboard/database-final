package com.cuterwrite.dbfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.service.AuthService;

/**  
 * @author Pang S.Z.
 * @create 2020-09-27 18:01:35 
 */
@RestController
public class JwtAuthController {
	
	@Autowired
	private AuthService authService;
	
	//登录
	@PostMapping(value = "/auth/login")
	public String login(String username,String password) {
		return authService.login(username, password);
	}
	
	//注册
	@PostMapping(value = "/auth/register")
	public User register(@RequestBody User addedUser) {
		return authService.register(addedUser);
	}
}
