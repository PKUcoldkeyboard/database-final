package com.cuterwrite.dbfinal.service;
/**
 * @author Pang S.Z.
 * @create 2021-01-24 08:58:54
 */

import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.entity.UserReceiveInfo;

public interface ReceiverService {
    /**
     * 查询
     */
    UserReceiveInfo getInfo(String username);

    /**
     * 增加
     */
    @Transactional
    void insert(UserReceiveInfo info);

    /**
     * 更新
     */
    @Transactional
    void update(Long id, UserReceiveInfo info);

    /**
     * 删除
     */
    @Transactional
    void delete(Long id);
}
