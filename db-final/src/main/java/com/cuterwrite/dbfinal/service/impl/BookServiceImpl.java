package com.cuterwrite.dbfinal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.BookDAO;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.service.BookService;
import com.cuterwrite.dbfinal.service.RedisService;
import com.cuterwrite.dbfinal.util.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.json.JSONUtil;

/**  
 * @author Pang S.Z.
 * @create 2021-01-23 14:01:50 
 */
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookDAO dao;
	@Autowired
	RedisService redisService;
	
	@Override
	public int insert(Book book) {
		redisService.set("book"+book.getId(), JSONUtil.toJsonStr(book));
		return dao.insert(book);
	}

	@Override
	public int update(Long id, Book book) {
		redisService.set("book"+id, JSONUtil.toJsonStr(book));
		return dao.updateByPrimaryKey(book);
	}

	@Override
	public Book select(Long id) {
		//先从缓存中获取
		String value=redisService.get("book"+id);
		if(value!=null) {
			return JSONUtil.toBean(value, Book.class);
		}
		Book book=dao.selectByPrimaryKey(id);
		redisService.set("book"+id, JSONUtil.toJsonStr(book));
		return book;
	}

	@Override
	public int delete(Long id) {
		redisService.remove("book"+id);
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Page<Book> list(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Book>bookList=dao.selectAll();
		return Page.restPage(bookList);
	}
}
