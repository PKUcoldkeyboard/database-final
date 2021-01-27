package com.cuterwrite.dbfinal.dao;

import com.cuterwrite.dbfinal.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * CommentDAO继承基类
 */
@Repository
public interface CommentDAO extends MyBatisBaseDao<Comment, Long> {
}