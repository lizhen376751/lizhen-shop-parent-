package com.lizhen.service;

import com.lizhen.api.entity.UserEntity;
import com.lizhen.api.service.MemberService;
import com.lizhen.base.BaseApiService;
import com.lizhen.base.ResponseBase;
import com.lizhen.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息接口实现类
 * Created by lizhen on 2018/4/20 0020.
 */
@RequestMapping("/member")
@RestController
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private BaseApiService baseApiService;

    @Override
    public ResponseBase findUserById(Long userId) {
        if (null==userId){
            return baseApiService.setSuccessResponseBase();
        }
        UserEntity byID = memberDao.findByID(userId);
        return baseApiService.setSuccessResponseBase(byID);
    }

    @Override
    public ResponseBase regUser(@RequestBody UserEntity user) {
        Integer integer = memberDao.insertUser(user);
        return baseApiService.setSuccessResponseBase(integer);
    }
}
