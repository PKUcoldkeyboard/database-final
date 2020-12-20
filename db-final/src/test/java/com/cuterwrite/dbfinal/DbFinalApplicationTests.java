package com.cuterwrite.dbfinal;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.service.TestService;






@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	TestService service;
	@Test
	void contextLoads() {
		service.insertUserInfo();
	}

}
