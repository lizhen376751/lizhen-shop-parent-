package com.lizhen.api.service;

import com.lizhen.api.entity.UserEntity;
import com.lizhen.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息的接口
 * Created by lizhen on 2018/4/20 0020.
 */
@RequestMapping("/member")
public interface MemberService {
    // 使用userId查找用户信息
    @RequestMapping("/findUserById")
    ResponseBase findUserById(Long userId);
    @RequestMapping("/regUser")
    ResponseBase regUser(@RequestBody UserEntity user);
}
