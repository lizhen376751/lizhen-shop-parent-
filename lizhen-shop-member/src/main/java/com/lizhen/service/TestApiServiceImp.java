package com.lizhen.service;

import com.lizhen.api.service.TestApiService;
import com.lizhen.base.BaseApiService;
import com.lizhen.base.BaseRedisService;
import com.lizhen.base.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhen on 2018/4/20 0020.
 */
@RestController
public class TestApiServiceImp extends BaseApiService implements TestApiService {
    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public Map<String, Object> test(Integer id, String name) {
        Map<String, Object> map = new HashMap();
        map.put("rtcode", "200");
        map.put("rtmsg", "success");
        map.put("data", new String[]{"123", "1234", "12345", "123456", id + "", name + ""});
        return map;
    }

    @Override
    public ResponseBase testResponseBase(Integer id, String name) {
        return setSuccessResponseBase();
    }

    @Override
    public ResponseBase testRedis(String key, String value) {
        baseRedisService.setString(key, value);
        return setSuccessResponseBase();
    }
}
