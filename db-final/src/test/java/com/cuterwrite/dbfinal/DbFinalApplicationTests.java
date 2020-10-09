package com.cuterwrite.dbfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.component.CancelOrderSender;



@SpringBootTest
class DbFinalApplicationTests {
	
	@Autowired
	CancelOrderSender cancelOrderSender;
	
	@Test
	void contextLoads() {
		long delayTime=30*1000;
		cancelOrderSender.sendMessage(1L, delayTime);
	}

}
