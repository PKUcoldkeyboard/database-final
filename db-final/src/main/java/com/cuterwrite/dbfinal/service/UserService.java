package com.cuterwrite.dbfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.entity.User;

import cn.hutool.json.JSONUtil;

/**
 * @author Pang S.Z.
 * @create 2020-09-27 17:44:37
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDAO userDao;
    @Autowired
    RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //先从缓存取数据
        String value = redisService.get(username);
        if (value != null) {
            return JSONUtil.toBean(redisService.get(username), User.class);
        }
        //缓存中没有从数据库中获取
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //写入缓存
        redisService.set(username, JSONUtil.toJsonStr(user));
        return user;
    }

}
