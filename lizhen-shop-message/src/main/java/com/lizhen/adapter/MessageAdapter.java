package com.lizhen.adapter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 *  统一发送消息接口
 * Created by lizhen on 2018/4/20 0020.
 */
@Component
public interface MessageAdapter {
    public void sendMsg(JSONObject body);
}
