package com.cuterwrite.dbfinal;






import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.service.BookSearchService;







@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	BookSearchService service;
	@Test
	void contextLoads() {

	}

}
