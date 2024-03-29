package com.cuterwrite.dbfinal.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.CartItemDAO;
import com.cuterwrite.dbfinal.entity.BookAndCart;
import com.cuterwrite.dbfinal.entity.CartItem;
import com.cuterwrite.dbfinal.service.CartService;
import com.cuterwrite.dbfinal.service.RedisService;
import com.cuterwrite.dbfinal.util.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.json.JSONUtil;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 01:58:02
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartItemDAO dao;
    @Autowired
    RedisService redisService;

    @Override
    public int insert(CartItem item) {
        dao.insert(item);
        Long id = dao.selectLast();
        item.setId(id);
        redisService.set("cart" + id, JSONUtil.toJsonStr(item));
        return id.intValue();
    }

    @Override
    public int update(Long id, CartItem item) {
        //先从缓存拿
        String value = redisService.get("cart" + id);
        CartItem origin = new CartItem();
        if (value != null) {
            origin = JSONUtil.toBean(value, CartItem.class);
        } else {
            origin = dao.selectByPrimaryKey(id);
        }
        origin.setQuantity(item.getQuantity());
        origin.setModifyTime(new Date());
        redisService.set("cart" + id, JSONUtil.toJsonStr(origin));
        return dao.updateByPrimaryKeySelective(origin);
    }

    @Override
    public Page<BookAndCart> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookAndCart> itemList = dao.selectAll();
        return Page.restPage(itemList);
    }

    @Override
    public int delete(Long id) {
        redisService.remove("cart" + id);
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public BookAndCart select(Long id) {
        //先从缓存取数据
        String value = redisService.get("cart" + id);
        if (value != null) {
            return JSONUtil.toBean(value, BookAndCart.class);
        }
        BookAndCart item = dao.selectOne(id);
        redisService.set("cart" + id, JSONUtil.toJsonStr(item));
        return item;
    }

}
