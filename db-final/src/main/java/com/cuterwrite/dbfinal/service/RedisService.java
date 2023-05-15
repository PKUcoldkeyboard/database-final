package com.cuterwrite.dbfinal.service;

/**
 * redis操作Service,
 * 对象和数组都以json形式存储
 *
 * @author Pang S.Z.
 * @create 2020-10-06 22:59:47
 */
public interface RedisService {
    /**
     * @param key
     * @param value
     * @Description 存储数据
     */
    void set(String key, String value);

    /**
     * @param key
     * @return
     * @Description 获取数据
     */
    String get(String key);

    /**
     * @param key
     * @param expire
     * @return
     * @Description 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * @param key
     * @Description 删除数据
     */
    void remove(String key);

    /**
     * @param key
     * @param delta 自增步长
     * @return
     * @Description 自增操作
     */
    Long increment(String key, long delta);
}
