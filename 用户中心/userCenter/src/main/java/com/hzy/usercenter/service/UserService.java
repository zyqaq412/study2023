package com.hzy.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.usercenter.entity.User;

/**
 * @title: UserService
 * @Author zxwyhzy
 * @Date: 2023/3/5 16:21
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    /**
     *  用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 用户id
     */
    long userRegister (String userAccount,String userPassword,String checkPassword);
}
