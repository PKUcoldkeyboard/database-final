package com.cuterwrite.dbfinal.service;

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.entity.BookAndCart;
import com.cuterwrite.dbfinal.entity.CartItem;
import com.cuterwrite.dbfinal.util.Page;

/**
 * 购物车相关
 *
 * @author Pang S.Z.
 * @create 2021-01-23 01:48:47
 */
public interface CartService {
    /**
     * 添加购物车
     */
    @Transactional
    int insert(CartItem item);

    /**
     * 更新购物车
     */
    @Transactional
    int update(Long id, CartItem item);

    /**
     * 分页查询购物车
     */
    Page<BookAndCart> list(Integer pageNum, Integer pageSize);

    /**
     * 删除购物车项
     */
    @Transactional
    int delete(Long id);

    /**
     * 查询购物车项
     */
    BookAndCart select(Long id);

}
