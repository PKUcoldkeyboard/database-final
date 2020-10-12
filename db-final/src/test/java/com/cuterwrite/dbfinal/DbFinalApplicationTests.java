package com.cuterwrite.dbfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.component.CancelOrderSender;
import com.cuterwrite.dbfinal.dao.RoleDAO;
import com.cuterwrite.dbfinal.entity.Role;



@SpringBootTest
class DbFinalApplicationTests {
	
	@Autowired
	RoleDAO roleDao;
	@Autowired
	CancelOrderSender cancelOrderSender;
	
	@Test
	void contextLoads() {
		Role role=roleDao.selectByPrimaryKey(1);
		System.out.println(role.toString());
	}

}
