package com.cuterwrite.dbfinal.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.service.RedisService;

/**  
 * redis操作实现类
 * @author Pang S.Z.
 * @create 2020-10-06 23:02:42 
 */
@Service
public class RedisServiceImpl implements RedisService{
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public boolean expire(String key, long expire) {
		return stringRedisTemplate.expire(key, expire,TimeUnit.SECONDS);
	}

	@Override
	public void remove(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public Long increment(String key, long delta) {
		return stringRedisTemplate.opsForValue().increment(key,delta);
	}

}
