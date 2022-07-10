package com.example.demo.mapper;

import com.example.demo.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int save(User user);

    User findByPhone(@Param("phone") String phone);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String md5);

    User findByUserId(@Param("user_id") Integer userId);
}
