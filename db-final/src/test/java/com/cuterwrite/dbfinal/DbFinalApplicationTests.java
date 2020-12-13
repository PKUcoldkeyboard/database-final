package com.cuterwrite.dbfinal;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.service.Neo4jService;

import cn.hutool.http.HttpUtil;





@SpringBootTest
class DbFinalApplicationTests {
	
	@Test
	void contextLoads() {
		HttpUtil.get("https://sc.ftqq.com/SCU131539T7e6c5576ee0d756dc308a320937a50045fc5ace7252a3.send?text=谢源，阿囊死给&desp=");
	}

}
