package com.lizhen.api.service;

import com.lizhen.api.entity.UserEntity;
import com.lizhen.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户信息的接口
 * Created by lizhen on 2018/4/20 0020.
 */
@RequestMapping("/member")
public interface MemberService {
    // 使用userId查找用户信息
    @RequestMapping("/findUserById")
    ResponseBase findUserById(Long userId);

    //注册
    @RequestMapping("/regUser")
    ResponseBase regUser(@RequestBody UserEntity user);

    //登录
    @RequestMapping("/login")
    ResponseBase login(@RequestBody UserEntity user);

    //根据token进行登录
    @RequestMapping("/loginByMemberToken")
    ResponseBase loginByMemberToken(@RequestParam("memToken")  String memToken);

}
