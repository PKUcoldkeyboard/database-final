package com.cuterwrite.dbfinal.service;


import com.cuterwrite.dbfinal.dto.ChangePwdParam;
import com.cuterwrite.dbfinal.dto.FindPwdParam;
import com.cuterwrite.dbfinal.entity.User;

/**
 * @author Pang S.Z.
 * @create 2020-09-27 17:13:20
 */
public interface AuthService {

    /**
     * @param userToAdd
     * @return User
     * @Description 注册
     */
    User register(User userToAdd, String email);

    /**
     * @param username
     * @param password
     * @return Json Web Token
     * @Description 登录
     */
    String login(String username, String password);

    /**
     * @param token
     * @return 新的token
     * @Description 刷新token
     */
    String refreshToken(String token);

    /**
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return 修改密码后的user
     * @Description 修改密码
     */
    User changePwd(ChangePwdParam changePwdParam);

    /**
     * @param param
     * @Description 找回密码
     */
    void findPwd(FindPwdParam param);
}
