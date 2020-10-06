package com.cuterwrite.dbfinal.service;
/**  
 * redis操作Service,
 * 对象和数组都以json形式存储
 * @author Pang S.Z.
 * @create 2020-10-06 22:59:47 
 */
public interface RedisService {
	/**
	 * 
	 * @Description 存储数据
	 * @param key
	 * @param value
	 */
	void set(String key,String value);
	/**
	 * 
	 * @Description 获取数据
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 
	 * @Description 设置超期时间 
	 * @param key
	 * @param expire
	 * @return
	 */
	boolean expire(String key,long expire);
	/**
	 * 
	 * @Description 删除数据
	 * @param key
	 */
	void remove(String key);
	/**
	 * 
	 * @Description 自增操作
	 * @param key
	 * @param delta 自增步长
	 * @return
	 */
	Long increment(String key,long delta);
}
