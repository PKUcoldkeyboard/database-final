package com.cuterwrite.dbfinal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.service.BookService;
import com.cuterwrite.dbfinal.util.Page;

import cn.hutool.core.bean.BeanUtil;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 14:12:36
 */
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/item")
    public ResponseResult findItemList(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize) {
        Page<Book> bookList = bookService.list(pageNum, pageSize);
        Map<String, Object> map = BeanUtil.beanToMap(bookList);
        return ResponseResult.ok().data(map);
    }

    @GetMapping("/item/query/{id}")
    public ResponseResult findItemOne(@PathVariable("id") Long id) {
        Book book = bookService.select(id);
        return ResponseResult.ok().data(BeanUtil.beanToMap(book));
    }

    @PostMapping("/item")
    public ResponseResult addBook(@RequestBody Book book) {
        bookService.insert(book);
        return ResponseResult.ok();
    }

    @PostMapping("/item/{id}")
    public ResponseResult updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.update(id, book);
        return ResponseResult.ok();
    }

    @DeleteMapping("/item/{id}")
    public ResponseResult deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseResult.ok();
    }
}
