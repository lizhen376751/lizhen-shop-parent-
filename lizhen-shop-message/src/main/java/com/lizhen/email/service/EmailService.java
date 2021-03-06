package com.lizhen.email.service;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 调用第三方处理email发送
 * Created by lizhen on 2018/4/20 0020.
 */
@Component
@Slf4j
public class EmailService implements MessageAdapter {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${msg.subject}")
    private String subject;
    @Value("${msg.text}")
    private String text;
    @Autowired
    private JavaMailSender javaMailSender;
    //重写接口里面发送消息的信息
    @Override
    public void sendMsg(JSONObject body) {
        //从body里面将邮箱地址消息取出
        String email = (String) body.get("email");
        if (StringUtils.isEmpty(email)) {
            return;
        }
        log.info("消息服务平台发送邮件:{}", email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送者
        simpleMailMessage.setFrom(username);
        //邮件接受者
        simpleMailMessage.setTo(email);
        //设置标题
        simpleMailMessage.setSubject(subject);
        //设置内容
        simpleMailMessage.setText(text.replace("{}",email));
        //发送邮件
        javaMailSender.send(simpleMailMessage);
    }
}
