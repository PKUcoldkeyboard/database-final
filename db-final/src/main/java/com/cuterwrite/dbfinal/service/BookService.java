package com.cuterwrite.dbfinal.service;
/**
 * 图书表
 *
 * @author Pang S.Z.
 * @create 2021-01-23 13:58:31
 */

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.util.Page;

public interface BookService {
    /**
     * 增加图书
     */
    @Transactional
    int insert(Book book);

    /**
     * 更新
     */
    @Transactional
    int update(Long id, Book book);

    /**
     * 查询
     */
    Book select(Long id);

    /**
     * 删除
     */
    @Transactional
    int delete(Long id);

    /**
     * 分页查询
     */
    Page<Book> list(Integer pageNum, Integer pageSize);
}
