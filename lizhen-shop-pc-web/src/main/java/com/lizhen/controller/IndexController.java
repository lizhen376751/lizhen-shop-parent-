package com.lizhen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制层
 * Created by lizhen on 2018/4/21.
 */
@Controller
public class IndexController {
    public static final String INDEX = "index";

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return INDEX;
    }

}
