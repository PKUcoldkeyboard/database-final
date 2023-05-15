package com.cuterwrite.dbfinal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 发送邮件工具类
 *
 * @author Pang S.Z.
 * @create 2020-12-18 10:44:41
 */
@Component
public class MailUtil {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    @Async
    public void sendMail(String receiver, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(receiver);
        message.setSubject(title);
        message.setText(content);
        javaMailSender.send(message);
    }
}
