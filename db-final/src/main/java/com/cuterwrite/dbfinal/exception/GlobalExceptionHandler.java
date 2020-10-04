package com.cuterwrite.dbfinal.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.common.ResultCode;

@ControllerAdvice
/**
 * 全局异常捕获
 * @author Pang S.Z.
 * @create 2020-09-26 16:48:00
 */
public class GlobalExceptionHandler {
	
	//通用异常处理
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult error(Exception e) {
		e.printStackTrace();
		return ResponseResult.error();
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ResponseResult error(NullPointerException e) {
		e.printStackTrace();
		return ResponseResult.setResult(ResultCode.NULL_POINT);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public ResponseResult error(IndexOutOfBoundsException e) {
		e.printStackTrace();
		return ResponseResult.setResult(ResultCode.HTTP_CLIENT_ERROR);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseBody
	public ResponseResult error(BadCredentialsException e) {
		e.printStackTrace();
		return ResponseResult.error().message("用户名或密码错误");
	}
	
	//自定义异常处理
	@ExceptionHandler(CMSException.class)
	@ResponseBody
	public ResponseResult error(CMSException e) {
		e.printStackTrace();
		return ResponseResult.error().message(e.getMessage()).code(e.getCode());
	}
}
