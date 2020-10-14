package com.cuterwrite.dbfinal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * 请求日志封装类
 * @author Pang S.Z.
 * @create 2020-10-07 16:51:22 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {
	/*
	 * 操作用户
	 */
	private String username;
	/*
	 * 操作时间
	 */
	private Long startTime;
	/*
	 * 所用时间
	 */
	private Integer spendTime;
	/*
	 * 根路径
	 */
	private String basePath;
	/*
	 * URI
	 */
	private String uri;
	/*
	 * URL
	 */
	private String url;
	/*
	 * 请求类型
	 */
	private String method;
	/*
	 * IP地址
	 */
	private String ip;
	/*
	 * 请求参数
	 */
	private Object parameter;
	/*
	 * 请求返回结果
	 */
	private Object result;
}
