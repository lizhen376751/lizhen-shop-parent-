package com.lizhen.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lizhen.adapter.MessageAdapter;
import com.lizhen.constants.Constants;
import com.lizhen.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by lizhen on 2018/4/20.
 * MQ的协议模板
 * {
 * "header": {
 * "interfaceType": "接口类型"
 * },
 * "content": {}
 * }
 */
@Component
@Slf4j
public class ConsumerDistribute {
    @Autowired
    private EmailService emailService;
    @Autowired
    private MessageAdapter messageAdapter;

    // 监听消息
    @JmsListener(destination = "messages_queue")
    public void distribute(String jsonmsg) {
        log.info("======================接受消息为：" + jsonmsg);
        //判断消息是否为空？
        if (StringUtils.isEmpty(jsonmsg)) {
            return;
        }
        //将字符串转换成json对象
        JSONObject jsonObject = JSON.parseObject(jsonmsg);
        //从json对象里面将报文头取出
        JSONObject header = (JSONObject) jsonObject.get("header");

        //判断是否为空
        if (null == header) {
            return;
        }
        //将接口类型信息取出
        String interfaceType = (String) header.get("interfaceType");
        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }

        //判断接口类型信息是什么？
        if (Constants.MSG_EMAIL.equals(interfaceType)) {
            messageAdapter = emailService;
        }
        //如果接口信息为空，说明没有匹配的类型
        if (null == messageAdapter) {
            return;
        }
        messageAdapter.sendMsg((JSONObject) jsonObject.get("content"));
    }
}
