package com.lizhen.service;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.api.entity.UserEntity;
import com.lizhen.api.service.MemberService;
import com.lizhen.base.BaseApiService;
import com.lizhen.base.BaseRedisService;
import com.lizhen.base.ResponseBase;
import com.lizhen.constants.Constants;
import com.lizhen.dao.MemberDao;
import com.lizhen.mq.RegisterMailboxProducer;
import com.lizhen.utils.MD5Util;
import com.lizhen.utils.TokenUtils;
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
    //MQ的方法
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    //redis方法封装
    @Autowired
    private BaseRedisService baseRedisService;
    //获取队列名称
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
        user.setCreated(new Date());
        user.setUpdated(new Date());
        Integer integer = memberDao.insertUser(user);

        if (integer <= 0) {
            return baseApiService.setErrorResponseBase("用户注册失败。。。");
        }
        String email = user.getEmail();
        JSONObject jsonObject = eamilJson(email);
        sendMsg(jsonObject.toJSONString());
        return baseApiService.setSuccessResponseBase(integer);
    }

    @Override
    public ResponseBase login(@RequestBody UserEntity user) {
        //判断用户名和密码是否为空
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username)) {
            return baseApiService.setErrorResponseBase("用户名为空");
        }

        if (StringUtils.isEmpty(password)) {
            return baseApiService.setErrorResponseBase("密码为空");
        }
        //将密码进行md5加密，然后查询数据库
        password = MD5Util.MD5(password);
        //判断数据库是否由此数据
        UserEntity byUserName = memberDao.findByUserName(username, password);
        if (null == byUserName) {
            return baseApiService.setErrorResponseBase("用户名或密码不正确");
        }
        //生成token=key，userid=value，存入redis
        String memberToken = TokenUtils.getMemberToken();
        baseRedisService.setString(memberToken, byUserName.getId() + "", Constants.TOKEN_MEMBER_TIME);
        //将token返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memberToken", memberToken);
        return baseApiService.setSuccessResponseBase(jsonObject);
    }

    @Override
    public ResponseBase loginByMemberToken(String memToken) {
        //判断token是否为空
        if (org.apache.commons.lang.StringUtils.isEmpty(memToken)) {
            return baseApiService.setErrorResponseBase("memberToken不能为空");
        }
        //根据token查询redis数据库
        String redisuserid = baseRedisService.getString(memToken);

        //判断查询结果是否为空
        if (StringUtils.isEmpty(redisuserid)) {
            return baseApiService.setErrorResponseBase("token已过期或无效token");
        }
        //非空,跟句userid查询数据库
        UserEntity byID = memberDao.findByID(Long.parseLong(redisuserid));
        //判断数据库是否存在
        if (null == byID) {
            return baseApiService.setErrorResponseBase("用户不存在");
        }
        byID.setPassword(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", byID);

        return baseApiService.setSuccessResponseBase(jsonObject);
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
