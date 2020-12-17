package com.cuterwrite.dbfinal;



import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.dao.BookDAO;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.service.Neo4jService;

import cn.hutool.http.HttpUtil;





@SpringBootTest
class DbFinalApplicationTests {
	
	@Autowired
	BookDAO bookDao;
	
	@Test
	void contextLoads() {
		Book book=new Book();
		book.setAlbumPicture("");
		book.setAuthor("");
		book.setCategoryId("");
		book.setCreateTime(new Date());
		book.setDescription("");
		book.setIsbn("");
		book.setLanguage("");
		book.setModifyTime(new Date());
		book.setName("");
		book.setPageCount(0);
		book.setPicture("");
		book.setPicture("");
		book.setPress("");
		book.setPrice(new BigDecimal(String.valueOf("1")));
		book.setPublishDate(new Date());
		book.setSale(1);
		book.setStock(1);
		bookDao.insert(book);
		
	}

}
