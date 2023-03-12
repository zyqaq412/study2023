package com.hzy.usercenter.controller;

import com.hzy.usercenter.entity.User;
import com.hzy.usercenter.entity.request.UserLoginRequest;
import com.hzy.usercenter.entity.request.UserRegisterRequest;
import com.hzy.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: UserController 用户接口
 * @Author zxwyhzy
 * @Date: 2023/3/12 12:58
 * @Version 1.0
 */

@RestController // 适用于编写restful风格的api，返回值默认为json类型
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest user){
        if (null == user) return null;
        return userService
                .userRegister(user.getUserAccount(),
                        user.getUserPassword(),
                        user.getCheckPassword());
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest user,HttpServletRequest request){
       return userService
               .userLogin(user.getUserAccount(),
                       user.getUserPassword(),
                       request);
    }


}
