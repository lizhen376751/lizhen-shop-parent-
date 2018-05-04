package com.lizhen.Controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;


/**
 * Created by Administrator on 2018/5/3.
 */

@ApiModel(value = "User得实体，----》",reference = "我是参考")
public class SwaggerUser {
    @ApiParam(value = "姓名--------------",required = true)
    private String name;
    //在swagger-ui.html#页面中如果返回User，ModelModel Schema选项卡可见
    @ApiModelProperty(value = "id",dataType = "String")
    private String id;
    //在http://localhost:8080/v2/api-docs最后一行的实体可见
    @ApiModelProperty(value = "年龄----------",required = true)
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SwaggerUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}
