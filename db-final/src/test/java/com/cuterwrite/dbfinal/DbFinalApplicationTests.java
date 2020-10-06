package com.cuterwrite.dbfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.service.RedisService;

@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	RedisService redisService;
	
	@Test
	void contextLoads() {
		System.out.println(redisService.get("auth"));
	}

}
