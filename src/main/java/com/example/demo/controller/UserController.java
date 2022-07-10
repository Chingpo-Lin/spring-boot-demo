package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * register
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        int rows = userService.save(userInfo);
        return rows == 1? JsonData.buildSuccess():JsonData.buildError("error in register");
    }

    /**
     * login
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());
        return token == null ? JsonData.buildError("登入失败，账号密码错误"): JsonData.buildSuccess(token);
    }

    /**
     * find info by user id
     * @param request
     * @return
     */
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request) {
        System.out.println("here");
        Integer userId = (Integer) request.getAttribute("user_id");

        if (userId == null) {
            return JsonData.buildError("error find");
        }

        User user = userService.findByUserId(userId);
        return JsonData.buildSuccess(user);
    }
}
