package com.example.demo.service.impl;

import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.BaseCache;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.save(user);
        }
        return -1;
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
        if (user == null) {
            return null;
        } else {
            return JWTUtils.geneJsonWebToken(user);
        }
    }

    @Override
    public User findByUserId(Integer userId) {

        User user = userMapper.findByUserId(userId);
//        user.setPwd("");
        return user;
    }

    private User parseToUser(Map<String, String> userInfo) {
        if (userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")) {
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRondomImg());
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");
            // use MD5
            user.setPwd(CommonUtils.MD5(pwd));

            return user;
        } else {
            return null;
        }
    }

    /**
     * CDN随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn- beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn- beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn- beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn- beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn- beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRondomImg() {
        int size = headImg.length;

        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}
