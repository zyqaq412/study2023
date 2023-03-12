package com.hzy.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.usercenter.contant.UserConstant;
import com.hzy.usercenter.entity.User;
import com.hzy.usercenter.mapper.UserMapper;
import com.hzy.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: UserServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/3/5 16:26
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String SALT = "zy";


    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        /*if (null != userAccount && null != userAccount && null != checkPassword){
        }*/
        // 不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = count(queryWrapper);
        if (count>0) return -1;
        if (userPassword.equals(checkPassword)){
            User user = new User();
            user.setUseraccount(userAccount);
            String password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            user.setUserpassword(password);
            boolean save = save(user);
            if (!save){
                return -1;
            }
            return  user.getId();
        }

        return -1;
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        userPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",userPassword);
        User user = getOne(queryWrapper);
        if (null == user) {
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }
        // 记录用户登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE,user);

        return user;
    }
}
