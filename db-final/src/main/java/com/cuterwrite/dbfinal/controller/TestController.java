package com.cuterwrite.dbfinal.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.dao.BookDAO;
import com.cuterwrite.dbfinal.dao.BookDiscountDAO;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.entity.BookDiscount;
import com.cuterwrite.dbfinal.service.BookService;


/**  
 * @author Pang S.Z.
 * @create 2020-09-30 21:44:30 
 */
@RestController
public class TestController {
	
	@Autowired
	BookDiscountDAO dao;
	@Autowired
	BookDAO dao2;
	@Autowired
	BookService bookService;
	
	@GetMapping("/test")
	public ResponseResult test() {
		List<Book>books=dao2.selectAll();
		BookDiscount discount=new BookDiscount();
		for(int i=0;i<50;i++) {
			Collections.shuffle(books);
			Book book=books.get(0);
			discount.setBookId(book.getId());
			discount.setDiscount(new BigDecimal("0.7"));
			discount.setCreateTime(new Date());
			discount.setModifyTime(new Date());
			discount.setPrice(book.getPrice().multiply(new BigDecimal("0.7")));
			discount.setCount(1);
			dao.insert(discount);
		}
		return ResponseResult.ok();
	}
}
