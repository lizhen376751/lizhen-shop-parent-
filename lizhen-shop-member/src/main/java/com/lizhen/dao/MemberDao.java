package com.lizhen.dao;

import com.lizhen.api.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息的dao层
 * Created by lizhen on 2018/4/20 0020.
 */
@Mapper
public interface MemberDao {

    /**
     * 根据usreid查询用户信息
     *
     * @param userId
     * @return
     */
    @Select("select id,username,password,phone,email,created,updated from mb_user where id =#{userId}")
    UserEntity findByID(@Param("userId") Long userId);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Select("select id,username,password,phone,email,created,updated from mb_user where username =#{username} and password=#{password}")
    UserEntity findByUserName(@Param("username") String username, @Param("password") String password);

    /**
     * 插入数据
     *
     * @param userEntity
     * @return
     */
    @Insert("INSERT  INTO `mb_user`  (username,password,phone,email,created,updated) VALUES (#{username}, #{password},#{phone},#{email},#{created},#{updated});")
    Integer insertUser(UserEntity userEntity);
}
