package com.cuterwrite.dbfinal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.service.ValidateMailService;

/**  
 * @author Pang S.Z.
 * @create 2020-12-18 14:09:43 
 */
@RestController
public class MailController {
	@Autowired
	ValidateMailService validateMailService;
	
	@GetMapping("/sendEmail")
	public ResponseResult sendEmail(@RequestParam String receiver,HttpSession session) {
		String code=validateMailService.sendEmail(receiver);
		session.setAttribute("verification", code);
		return ResponseResult.ok();
	}
}
