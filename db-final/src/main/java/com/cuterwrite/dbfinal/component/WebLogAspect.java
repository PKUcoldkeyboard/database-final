package com.cuterwrite.dbfinal.component;



import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cuterwrite.dbfinal.entity.WebLog;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;

/**  
 * 统一日志处理切面
 * @author Pang S.Z.
 * @create 2020-10-07 16:36:02 
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
	private static Logger LOGGER=LoggerFactory.getLogger(WebLogAspect.class);
	
	@Pointcut("execution(public * com.cuterwrite.dbfinal.controller.*.*(..))")
	public void webLog() {
		
	}
	
	@Pointcut("execution(public * com.cuterwrite.dbfinal.exception.GlobalExceptionHandler.*(..))")
	public void exceptionLog() {
		
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint)throws Throwable{
	}
	
	@AfterReturning(value = "webLog()",returning = "ret")
	public void doAfterReturning(Object ret)throws Throwable{
	}
	
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
		long startTime=System.currentTimeMillis();
		//获取当前请求对象
		ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		//记录请求信息
		WebLog webLog=new WebLog();
		Object result=joinPoint.proceed();
		Signature signature=joinPoint.getSignature();
		MethodSignature methodSignature=(MethodSignature)signature;
		Method method=methodSignature.getMethod();
		long endTime=System.currentTimeMillis();
		String urlStr=request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setIp(request.getRemoteAddr());
		webLog.setUsername(request.getRemoteUser());
		webLog.setMethod(request.getMethod());
		webLog.setParameter(getParameter(method, joinPoint.getArgs()));
		webLog.setResult(result);
		webLog.setSpendTime((int)(endTime-startTime));
		webLog.setStartTime(startTime);
		webLog.setUri(request.getRequestURI());
		webLog.setUrl(request.getRequestURL().toString());
		LOGGER.info("webLog:{}",JSONUtil.parseObj(webLog));
		return result;
	}

	/*
	 * 根据方法和传入的参数获得请求参数
	 */
	private Object getParameter(Method method,Object[]args) {
		List<Object>argList=new ArrayList<>();
		Parameter[]parameters=method.getParameters();
		for(int i=0;i<parameters.length;i++) {
			//将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody=parameters[i].getAnnotation(RequestBody.class);
			if(requestBody!=null) {
				argList.add(args[i]);
			}
			//将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam=parameters[i].getAnnotation(RequestParam.class);
			if(requestParam!=null) {
				Map<String, Object>map=new HashMap<>();
				String key=parameters[i].getName();
				if(!StrUtil.isEmpty(requestParam.value())) {
					key=requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if(argList.size()==0) {
			return null;
		}else if(argList.size()==1) {
			return argList.get(0);
		}else {
			return argList;
		}
	}
}
