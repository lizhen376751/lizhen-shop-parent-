package com.lizhen.controller;

import com.lizhen.api.entity.UserEntity;
import com.lizhen.base.ResponseBase;
import com.lizhen.fegin.MemServiceFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

/**
 * 用户注册控制层
 * Created by lizhen on 2018/4/21.
 */
@Controller
public class RegisterController {
    @Autowired
    private MemServiceFegin memServiceFegin;
    //注册页面地址
    public static final String REGISTE = "register";
    public static final String LOGIN = "login";

    /**
     * 访问注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return REGISTE;
    }

    /**
     * 进行注册接口
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(UserEntity userEntity, HttpServletRequest httpServletRequest) {
        //1.验证参数
        //2.调用接口
//        memServiceFegin.regUser(userEntity);
        ResponseBase responseBase = memServiceFegin.regUser(userEntity);
//        3.根据接口判断，是否成功，失败返回注册页面
        Integer rtncode = responseBase.getRtncode();
        if (rtncode != 200) {
            String rtnmsg = responseBase.getRtnmsg();
            httpServletRequest.setAttribute("error", rtnmsg);
            return REGISTE;
        }
        //4.成功跳转至其他页面
        return LOGIN;

    }
}
