package com.lizhen.controller;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.api.entity.UserEntity;
import com.lizhen.base.ResponseBase;
import com.lizhen.constants.Constants;
import com.lizhen.fegin.MemServiceFegin;
import com.lizhen.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * 登录控制层
 * Created by lizhen on 2018/4/22.
 */
@Controller
public class LoginController {
    @Autowired
    private MemServiceFegin memServiceFegin;
    private static final String INDEX = "redirect:/";
    public static final String LOGIN = "login";

    //请求登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return LOGIN;
    }

    //登录的具体处理逻辑
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(UserEntity userEntity, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //1.验参
        //2.请求登录接口
        ResponseBase login = memServiceFegin.login(userEntity);
        Integer rtncode = login.getRtncode();
        //3.判断返回参数
        if (!Constants.HTTP_RES_CODE_200.equals(rtncode)) {
            httpServletRequest.setAttribute("error", "账号或者密码错误!");
            return LOGIN;
        }
        LinkedHashMap loginData = (LinkedHashMap) login.getRtndata();
        String memberToken = (String) loginData.get("memberToken");
        if (StringUtils.isEmpty(memberToken)) {
            httpServletRequest.setAttribute("error", "会话已经失效!");
            return LOGIN;
        }

        //4.将token存放到cookie中
        setCookie(memberToken, httpServletResponse);
        return INDEX;
    }

    public void setCookie(String memberToken, HttpServletResponse response) {
        CookieUtil.addCookie(response, Constants.COOKIE_MEMBER_TOKEN, memberToken, Constants.COOKIE_TOKEN_MEMBER_TIME);
    }
}
