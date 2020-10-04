package com.cuterwrite.dbfinal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
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
	public ResponseResult login(String username,String password) {
		String token=authService.login(username, password);
		Map<String, Object>tokenMap=new HashMap<>();
		tokenMap.put("token", token);
		return ResponseResult.ok().data(tokenMap).message("登录成功");
	}
	
	//注册
	@PostMapping(value = "/auth/register")
	public ResponseResult register(@RequestBody User addedUser) {
		User user = authService.register(addedUser);
		Map<String, Object>map=new HashMap<>();
		map.put("user", user);
		return ResponseResult.ok().data(map);
	}
}
