package com.cuterwrite.dbfinal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.BookDiscountDAO;
import com.cuterwrite.dbfinal.entity.BookDiscount;
import com.cuterwrite.dbfinal.entity.DiscountedBook;
import com.cuterwrite.dbfinal.service.BookDiscountService;
import com.cuterwrite.dbfinal.service.RedisService;

import cn.hutool.json.JSONUtil;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 17:04:32
 */
@Service
public class BookDiscountServiceImpl implements BookDiscountService {
    @Autowired
    BookDiscountDAO dao;
    @Autowired
    RedisService redisService;

    @Override
    public int insert(BookDiscount bookDiscount) {
        redisService.set("bookDiscount" + bookDiscount.getId(), JSONUtil.toJsonStr(bookDiscount));
        return dao.insert(bookDiscount);
    }

    @Override
    public int update(Long id, BookDiscount bookDiscount) {
        redisService.set("bookDiscount" + id, JSONUtil.toJsonStr(bookDiscount));
        return dao.updateByPrimaryKeySelective(bookDiscount);
    }

    @Override
    public int delete(Long id) {
        redisService.remove("bookDiscount" + id);
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public List<DiscountedBook> selectAll() {
        return dao.selectAll();
    }

    @Override
    public BookDiscount select(Long id) {
        String value = redisService.get("bookDiscount" + id);
        if (value != null) {
            BookDiscount item = JSONUtil.toBean(value, BookDiscount.class);
            return item;
        }
        BookDiscount bookDiscount = dao.selectByPrimaryKey(id);
        redisService.set("bookDiscount" + id, JSONUtil.toJsonStr(bookDiscount));
        return bookDiscount;
    }

}
