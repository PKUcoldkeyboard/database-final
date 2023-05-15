package com.cuterwrite.dbfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.util.MailUtil;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 14:52:23
 */
@RequestMapping("/contact")
@RestController
public class ContactController {
    @Autowired
    MailUtil mailUtil;

    @PostMapping("/feedback")
    public ResponseResult send(@RequestParam String xs,
                               @RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String title,
                               @RequestParam String content) {
        String allContent = content + "\n" + xs + name + "\n" + email;
        mailUtil.sendMail("1800016616@pku.edu.cn", title, allContent);
        return ResponseResult.ok();
    }
}
