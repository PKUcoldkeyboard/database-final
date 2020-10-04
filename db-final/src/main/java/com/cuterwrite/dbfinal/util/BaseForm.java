package com.cuterwrite.dbfinal.util;
/** 
 * 抽象表单 
 * @author Pang S.Z.
 * @create 2020-10-04 00:13:45 
 */
public abstract class BaseForm<T>{
	/**
	 * 
	 * @Description 获取实例
	 * @return 返回实体类
	 */
	public abstract T buildEntity();
}
