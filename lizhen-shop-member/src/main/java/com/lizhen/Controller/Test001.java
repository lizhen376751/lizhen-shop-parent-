package com.lizhen.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/5.
 */
@RestController
@Api(value = "测试使用")
public class Test001 {
    @RequestMapping("/testmethod")
    @ApiOperation(value = "测试方法")
    public String testmetod(){
        return "success";
    }
}
