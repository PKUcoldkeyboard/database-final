package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.CommentReplyContent;
import org.springframework.stereotype.Repository;

/**
 * CommentReplyContentDAO继承基类
 */
@Repository
public interface CommentReplyContentDAO extends MyBatisBaseDao<CommentReplyContent, Long> {
}