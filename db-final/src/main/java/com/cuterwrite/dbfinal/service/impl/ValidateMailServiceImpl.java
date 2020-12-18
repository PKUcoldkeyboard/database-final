package com.cuterwrite.dbfinal.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.service.ValidateMailService;
import com.cuterwrite.dbfinal.util.MailUtil;

import cn.hutool.core.util.RandomUtil;

/**  
 * @author Pang S.Z.
 * @create 2020-12-18 14:01:29 
 */
@Service
public class ValidateMailServiceImpl implements ValidateMailService{
	
	@Autowired
	private MailUtil mailUtil;
	
	@Override
	public String sendEmail(String to) {
		//验证码
		String code=RandomUtil.randomString(6);
		String title="请验证您的邮箱地址";
		String content="请在相应位置输入如下验证码：\r\n" + 
				"\r\n\r\n" + code +
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"请注意该验证码在2小时内有效。\r\n" + 
				"\r\n" + 
				"此封邮件由系统自动发出，请不要直接回复。 如以上帐号并非您申请，请忽略此封邮件。\r\n";
		mailUtil.sendMail(to, title, content);
		return code;
	}
	
}
