package com.cuterwrite.dbfinal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.entity.BookDiscount;
import com.cuterwrite.dbfinal.entity.DiscountedBook;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 17:02:52
 */
public interface BookDiscountService {
    /**
     * 增加图书
     */
    @Transactional
    int insert(BookDiscount bookDiscount);

    /**
     * 更新
     */
    @Transactional
    int update(Long id, BookDiscount bookDiscount);

    /**
     * 查询
     */
    BookDiscount select(Long id);

    /**
     * 删除
     */
    @Transactional
    int delete(Long id);

    /**
     * 查询全部
     */
    List<DiscountedBook> selectAll();
}
