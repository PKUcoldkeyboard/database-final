package com.cuterwrite.dbfinal;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.service.Neo4jService;





@SpringBootTest
class DbFinalApplicationTests {
	
	@Autowired
	Neo4jService service;
	
	@Test
	void contextLoads() {
		service.clear();
	}

}
