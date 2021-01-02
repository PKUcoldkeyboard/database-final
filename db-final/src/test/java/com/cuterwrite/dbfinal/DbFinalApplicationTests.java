package com.cuterwrite.dbfinal;




import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuterwrite.dbfinal.elasticsearch.BookRepository;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.service.TestService;






@SpringBootTest
class DbFinalApplicationTests {
	@Autowired
	BookRepository repository;
	@Test
	void contextLoads() {
		Book book=new Book();
		/*
		 * book.setAlbumPicture("测试") .setAuthor("测试") .setCategoryId(1L)
		 * .setCreateTime(new Date()) .setModifyTime(new Date()) .setDescription("测试")
		 * .setId(1L) .setIsbn("测试") .setLanguage("测试") .setName("测试") .setPageCount(1)
		 * .setPicture("测试") .setPress("测试") .setPrice(new BigDecimal("99.9"))
		 * .setPublishDate(new Date()) .setSale(1) .setStock(1);
		 */
		List<Book>books=repository.findByName("adsad");
		Book b=books.get(0);
		System.out.println(b.toString());
	}

}
