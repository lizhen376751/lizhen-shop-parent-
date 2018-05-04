package com.lizhen.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Administrator on 2018/5/3.
 */
@Api(value = "用户操作接口", tags={"用户操作接口123"},description = "用户接口详情")
@RestController
@RequestMapping("/test")
public class TestController {

        static Map<String, SwaggerUser> users = Collections.synchronizedMap(new HashMap<String, SwaggerUser>());
        @ApiOperation(value="获取用户信息",tags={"获取用户信息"},notes="注意问题点")
        @RequestMapping(value={""}, method=RequestMethod.GET)
        public List<SwaggerUser> getUserList() {
            List<SwaggerUser> r = new ArrayList<SwaggerUser>(users.values());
            return r;
        }

        @ApiOperation(value="创建用户", notes="根据User对象创建用户")
        @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
        @RequestMapping(value="", method=RequestMethod.POST)
        public String postUser(@RequestBody SwaggerUser user) {
            users.put(user.getId(), user);
            return "success";
        }

        @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
        @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
        @RequestMapping(value="/{id}", method=RequestMethod.GET)
        public SwaggerUser getUser(@PathVariable String id) {
            return users.get(id);
        }

        @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
                @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
        })
        @RequestMapping(value="/{id}", method=RequestMethod.PUT)
        public String putUser(@PathVariable String id, @RequestBody SwaggerUser user) {
            SwaggerUser u = users.get(id);
            u.setName(user.getName());
            u.setAge(user.getAge());
            users.put(id, u);
            return "success";
        }

        @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
        @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
        @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
        public String deleteUser(@PathVariable String id) {
            users.remove(id);
            return "success";
        }


}
