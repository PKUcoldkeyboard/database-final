package com.cuterwrite.dbfinal;






import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.entity.EsBook;
import com.cuterwrite.dbfinal.service.BookSearchService;

import cn.hutool.core.bean.BeanUtil;







@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	BookSearchService service;
	@Test
	void contextLoads() {

	}

}
