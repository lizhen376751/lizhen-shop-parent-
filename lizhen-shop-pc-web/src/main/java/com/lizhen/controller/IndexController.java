package com.lizhen.controller;
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
import java.util.LinkedHashMap;

/**
 * 控制层
 * Created by lizhen on 2018/4/21.
 */
@Controller
public class IndexController {
    @Autowired
    MemServiceFegin memServiceFegin;
    public static final String INDEX = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest httpServletRequest) {

        // 1.从cookie中获取 token信息
        String token = CookieUtil.getUid(httpServletRequest, Constants.COOKIE_MEMBER_TOKEN);
        // 2.如果cookie 存在 token，调用会员服务接口，使用token查询用户信息
        if (!StringUtils.isEmpty(token)) {
            ResponseBase responseBase = memServiceFegin.loginByMemberToken(token);
            if (responseBase.getRtncode().equals(Constants.HTTP_RES_CODE_200)) {
                LinkedHashMap userData = (LinkedHashMap) responseBase.getRtndata();
                Object user = userData.get("user");
                httpServletRequest.setAttribute("username", user.toString());
            }
        }
        return INDEX;
    }

}
