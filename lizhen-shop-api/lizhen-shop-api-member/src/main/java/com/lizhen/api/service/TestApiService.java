package com.lizhen.api.service;

import com.lizhen.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by lizhen on 2018/4/20 0020.
 */
@RequestMapping("/member")
public interface TestApiService {
    @RequestMapping("/test")
    public Map<String, Object> test(Integer id, String name);

    @RequestMapping("/testResponseBase")
    public ResponseBase testResponseBase(Integer id, String name);

    @RequestMapping("/testRedis")
    public ResponseBase testRedis(String key, String value);
}
