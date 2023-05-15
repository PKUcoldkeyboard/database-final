package com.cuterwrite.dbfinal.service;


/**
 * 邮箱验证服务
 *
 * @author Pang S.Z.
 * @create 2020-12-18 13:57:20
 */
public interface ValidateMailService {
    /**
     * @param to      收件人邮箱
     * @param session
     * @Description 发送带有验证码的邮箱
     */
    String sendEmail(String to);
}
