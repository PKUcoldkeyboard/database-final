package com.cuterwrite.dbfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.service.RedisService;

import cn.hutool.json.JSONUtil;

@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	RedisService redisService;
	
	@Test
	void contextLoads() {
		User user=new User();
		user.setUsername("cuterwrite");
		user.setPassword("heart39100");
		redisService.set("cuterwrite", JSONUtil.toJsonStr(user));
		System.out.println(redisService.get("cuterwrite"));
	}

}
