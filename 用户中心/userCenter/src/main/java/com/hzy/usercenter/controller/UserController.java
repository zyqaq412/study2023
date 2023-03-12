package com.hzy.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzy.usercenter.contant.UserConstant;
import com.hzy.usercenter.entity.User;
import com.hzy.usercenter.entity.request.UserLoginRequest;
import com.hzy.usercenter.entity.request.UserRegisterRequest;
import com.hzy.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/search")
    public List<User> searchUsers(String username,HttpServletRequest request){
        if (!isAdmin(request)) return null;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        return userService.list(queryWrapper);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Long id,HttpServletRequest request){
        if (!isAdmin(request)) return false;

        if (id <=0) return false;
        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request){
        // 鉴权 仅管理员可查询
        Object userObject = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObject;
        return null!=user && user.getRole()==UserConstant.ADMIN_ROLE;
    }
}
