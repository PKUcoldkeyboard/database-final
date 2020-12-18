package com.cuterwrite.dbfinal.service;

import javax.servlet.http.HttpSession;

/**  
 * 邮箱验证服务
 * @author Pang S.Z.
 * @create 2020-12-18 13:57:20 
 */
public interface ValidateMailService {
	/**
	 * 
	 * @Description 发送带有验证码的邮箱
	 * @param to 收件人邮箱
	 * @param session
	 */
	void sendEmail(String to,HttpSession session);
}
