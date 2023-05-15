package com.cuterwrite.dbfinal.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.common.Const;
import com.cuterwrite.dbfinal.common.ResultCode;
import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.dao.UserEmailDAO;
import com.cuterwrite.dbfinal.dto.ChangePwdParam;
import com.cuterwrite.dbfinal.dto.FindPwdParam;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.entity.UserEmail;
import com.cuterwrite.dbfinal.exception.CMSException;
import com.cuterwrite.dbfinal.service.AuthService;
import com.cuterwrite.dbfinal.service.RedisService;
import com.cuterwrite.dbfinal.util.JwtTokenUtil;

import cn.hutool.json.JSONUtil;

/**
 * @author Pang S.Z.
 * @create 2020-09-27 17:15:39
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserEmailDAO userEmailDao;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User userToAdd, String email) {
        final String username = userToAdd.getUsername();
        if (userDao.findByUsername(username) != null) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "用户名已经被注册");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        redisService.set(username, JSONUtil.toJsonStr(userToAdd));
        userDao.insert(userToAdd);
        userDao.insertUserRole(userToAdd);

        UserEmail record = new UserEmail();
        record.setUserId((long) userDao.findByUsername(username).getId());
        record.setEmail(email);
        record.setCreateTime(new Date());
        record.setModifyTime(new Date());
        userEmailDao.insert(record);
        return userToAdd;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refreshToken(String token) {
        token = token.substring(Const.TOKEN_PREFIX.length());
        return jwtTokenUtil.refreshToken(token);
    }

    @Override
    public User changePwd(ChangePwdParam changePwdParam) {
        final String username = changePwdParam.getUsername();
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "用户名不存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = changePwdParam.getOldPassword();
        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "输入的旧密码不正确");
        } else {
            String newPassword = encoder.encode(changePwdParam.getNewPassword());
            user.setPassword(newPassword);
            redisService.set(username, JSONUtil.toJsonStr(user));
            userDao.update(user);
        }
        return user;
    }

    @Override
    public void findPwd(FindPwdParam param) {
        final String username = param.getUsername();
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "用户名不存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(param.getNewPassword()));
        redisService.set(username, JSONUtil.toJsonStr(user));
        userDao.update(user);
    }

}
