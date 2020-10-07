package com.cuterwrite.dbfinal.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.common.ResultCode;

import cn.hutool.json.JSONUtil;

/**
 * 当访问接口没有权限时，自定义的返回结果  
 * @author Pang S.Z.
 * @create 2020-09-30 21:57:27 
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(JSONUtil.parse(ResponseResult.error().message(ResultCode.FORBIDDEN.getMessage()).code(20006)));
		response.getWriter().flush();
	}

}
