package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.CommentReply;
import org.springframework.stereotype.Repository;

/**
 * CommentReplyDAO继承基类
 */
@Repository
public interface CommentReplyDAO extends MyBatisBaseDao<CommentReply, Long> {
}