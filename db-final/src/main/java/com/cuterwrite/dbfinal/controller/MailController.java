package com.cuterwrite.dbfinal.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.dao.UserEmailDAO;
import com.cuterwrite.dbfinal.service.ValidateMailService;

/**  
 * @author Pang S.Z.
 * @create 2020-12-18 14:09:43 
 */
@RestController
public class MailController {
	@Autowired
	ValidateMailService validateMailService;
	
	@Autowired
	UserEmailDAO dao;
	
	@PostMapping("/sendEmail")
	public ResponseResult sendEmail(@RequestBody Map<String,Object>map,HttpSession session) {
		String receiver=(String)map.get("receiver");
		String code=validateMailService.sendEmail(receiver);
		session.setAttribute("verification", code);
		return ResponseResult.ok();
	}
	
	@PostMapping("/sendEmailForPwd")
	public ResponseResult sendEmailForPwd(@RequestBody Map<String, Object>map,HttpSession session) {
		String username=(String)map.get("username");
		String receiver=dao.selectByUsername(username);
		String code=validateMailService.sendEmail(receiver);
		session.setAttribute("findPwdCode", code);
		return ResponseResult.ok();
	}
	
}
