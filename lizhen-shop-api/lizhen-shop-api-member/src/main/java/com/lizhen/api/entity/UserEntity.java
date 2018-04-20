package com.lizhen.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息实体类
 * Created by lizhen on 2018/4/20 0020.
 */
@Getter
@Setter
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
}
