package com.lizhen.service;

import com.lizhen.api.entity.UserEntity;
import com.lizhen.api.service.MemberService;
import com.lizhen.base.BaseApiService;
import com.lizhen.base.ResponseBase;
import com.lizhen.dao.MemberDao;
import com.lizhen.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息接口实现类
 * Created by lizhen on 2018/4/20 0020.
 */
@RestController
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private BaseApiService baseApiService;

    @Override
    public ResponseBase findUserById(Long userId) {
        if (null == userId) {
            return baseApiService.setSuccessResponseBase();
        }
        UserEntity byID = memberDao.findByID(userId);
        return baseApiService.setSuccessResponseBase(byID);
    }

    @Override
    public ResponseBase regUser(@RequestBody UserEntity user) {
        if (null == user) {
            return baseApiService.setErrorResponseBase("用户信息不能为空.....");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            return baseApiService.setErrorResponseBase("用户信息不能为空.....");
        }
        String md5 = MD5Util.MD5(password);
        user.setPassword(md5);
        Integer integer = memberDao.insertUser(user);

        if (integer <= 0) {
            return baseApiService.setErrorResponseBase("用户注册失败。。。");
        }
        return baseApiService.setSuccessResponseBase(integer);
    }
}
