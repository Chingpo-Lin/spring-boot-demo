package com.example.demo.service;

import com.example.demo.model.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * add user
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
