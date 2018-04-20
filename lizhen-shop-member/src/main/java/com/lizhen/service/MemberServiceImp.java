package com.lizhen.service;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.api.entity.UserEntity;
import com.lizhen.api.service.MemberService;
import com.lizhen.base.BaseApiService;
import com.lizhen.base.ResponseBase;
import com.lizhen.dao.MemberDao;
import com.lizhen.mq.RegisterMailboxProducer;
import com.lizhen.utils.MD5Util;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户信息接口实现类
 * Created by lizhen on 2018/4/20 0020.
 */
@RestController
public class MemberServiceImp implements MemberService {
    //dao层代码
    @Autowired
    private MemberDao memberDao;
    //基础的返回信息方法封装
    @Autowired
    private BaseApiService baseApiService;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Value("${messages.queue}")
    private String MESSAGESQUEUE;

    @Override
    public ResponseBase findUserById(Long userId) {
        if (null == userId) {
            return baseApiService.setSuccessResponseBase();
        }
        UserEntity byID = memberDao.findByID(userId);
        return baseApiService.setSuccessResponseBase(byID);
    }

    @Override
    public ResponseBase regUser(@RequestBody UserEntity user) {
        if (null == user) {
            return baseApiService.setErrorResponseBase("用户信息不能为空.....");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            return baseApiService.setErrorResponseBase("用户信息不能为空.....");
        }
        String md5 = MD5Util.MD5(password);
        user.setPassword(md5);
        Integer integer = memberDao.insertUser(user);

        if (integer <= 0) {
            return baseApiService.setErrorResponseBase("用户注册失败。。。");
        }
        String email = user.getEmail();
        JSONObject jsonObject = eamilJson(email);
        sendMsg(jsonObject.toJSONString());
        return baseApiService.setSuccessResponseBase(integer);
    }

    public JSONObject eamilJson(String email) {
        JSONObject jsonObject = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", "email");
        JSONObject content = new JSONObject();
        content.put("email", email);
        jsonObject.put("header", header);
        jsonObject.put("content", content);
        return jsonObject;
    }

    private void sendMsg(String json) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
        registerMailboxProducer.sendMsg(activeMQQueue, json);

    }

}
