package com.cuterwrite.dbfinal.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.common.ResultCode;

import cn.hutool.json.JSONUtil;

/**  
 * 当未登录或token失效访问接口时，自定义的返回结果
 * @author Pang S.Z.
 * @create 2020-09-30 22:00:11 
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(JSONUtil.parse(ResponseResult.error().message(ResultCode.UNAUTHORIZED.getMessage()).code(20005)));
		response.getWriter().flush();
	}

}
