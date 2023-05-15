package com.cuterwrite.dbfinal.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.entity.UserReceiveInfo;
import com.cuterwrite.dbfinal.service.ReceiverService;

import cn.hutool.core.bean.BeanUtil;

/**
 * @author Pang S.Z.
 * @create 2021-01-24 09:10:32
 */
@RequestMapping("/receiver")
@RestController
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;
    @Autowired
    UserDAO dao;

    @GetMapping("/info")
    public ResponseResult getInfo(@RequestParam String username) {
        return ResponseResult.ok().data(BeanUtil.beanToMap(receiverService.getInfo(username)));
    }

    @PostMapping("/info/{username}")
    public ResponseResult addInfo(@PathVariable("username") String username,
                                  @RequestBody UserReceiveInfo info) {
        User user = dao.findByUsername(username);
        info.setUserId(user.getId().longValue());
        info.setCreateTime(new Date());
        info.setModifyTime(new Date());
        receiverService.insert(info);
        return ResponseResult.ok();
    }
}
