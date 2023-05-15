package com.cuterwrite.dbfinal.service;


import org.springframework.data.domain.Page;

import com.cuterwrite.dbfinal.entity.EsBook;

/**
 * 搜索图书
 *
 * @author Pang S.Z.
 * @create 2021-01-22 15:12:20
 */
public interface BookSearchService {
    /**
     * 从数据库导入到ES
     */
    int importAll();

    /**
     * 创建一个索引
     */
    EsBook create(Long id);

    /**
     * 删除索引
     */
    void delete(Long id);

    /**
     * 批量删除
     */
    void deleteBatch(Long[] ids);

    /**
     * 删除全部
     */
    void deleteAll();

    /**
     * 更新
     */
    void update(Long id);

    /**
     * 全文搜索
     */
    Page<EsBook> search(String keyword, String categoryName, Integer pageNum, Integer pageSize, Integer sort);

}
