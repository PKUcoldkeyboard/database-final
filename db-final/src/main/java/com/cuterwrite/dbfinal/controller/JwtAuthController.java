package com.cuterwrite.dbfinal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.Const;
import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.common.ResultCode;
import com.cuterwrite.dbfinal.dto.ChangePwdParam;
import com.cuterwrite.dbfinal.dto.FindPwdParam;
import com.cuterwrite.dbfinal.dto.LoginParam;
import com.cuterwrite.dbfinal.dto.RegisterParam;
import com.cuterwrite.dbfinal.entity.Role;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.exception.CMSException;
import com.cuterwrite.dbfinal.service.AuthService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 用户认证
 *
 * @author Pang S.Z.
 * @create 2020-09-27 18:01:35
 */
@RequestMapping("/auth")
@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    //登录
    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody @Valid LoginParam loginParam) {
        String token = authService.login(loginParam.getUsername(), loginParam.getPassword());
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead", Const.TOKEN_PREFIX);
        tokenMap.put("token", token);
        return ResponseResult.ok().data(tokenMap).message("登录成功");
    }

    //普通用户注册
    @PostMapping(value = "/register")
    public ResponseResult register(@RequestBody @Valid RegisterParam registerParam, HttpSession session) {
        String code = session.getAttribute("verification").toString();
        if (!registerParam.getCode().equals(code)) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "邮箱验证码不正确");
        }
        User userToAdd = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ROLE_USER");
        roles.add(role);
        userToAdd.setUsername(registerParam.getUsername());
        userToAdd.setPassword(registerParam.getPassword());
        userToAdd.setRoles(roles);
        User user = authService.register(userToAdd, registerParam.getEmail());
        session.removeAttribute("verification");
        return ResponseResult.ok().data(BeanUtil.beanToMap(user));
    }

    //登出
    @PostMapping(value = "/logout")
    public ResponseResult logout() {
        return ResponseResult.ok();
    }

    //刷新token
    @GetMapping(value = "/refreshToken")
    public ResponseResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(Const.HEADER_STRING);
        String refreshToken = authService.refreshToken(token);
        if (refreshToken == null) {
            return ResponseResult.error().message("token已经过期！");
        }
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead", Const.TOKEN_PREFIX);
        tokenMap.put("token", refreshToken);
        return ResponseResult.ok().data(tokenMap);
    }

    //修改密码
    @PostMapping(value = "/changePwd")
    public ResponseResult changePwd(@Validated @RequestBody ChangePwdParam param) {
        User user = authService.changePwd(param);
        return ResponseResult.ok().data(BeanUtil.beanToMap(user));
    }

    @PostMapping(value = "/findPwd")
    public ResponseResult findPwd(@Validated @RequestBody FindPwdParam param, HttpSession session) {
        String code = session.getAttribute("findPwdCode").toString();
        if (!param.getCode().equals(code)) {
            throw new CMSException(ResultCode.PARAM_ERROR.getCode(), "邮箱验证码不正确");
        }
        authService.findPwd(param);
        session.removeAttribute("findPwdCode");
        return ResponseResult.ok();
    }
}
