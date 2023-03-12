package com.hzy.usercenter.entity.request;

import lombok.Data;

/**
 * @title: UserRegisterRequest 接收前端注册参数
 * @Author zxwyhzy
 * @Date: 2023/3/12 13:05
 * @Version 1.0
 */
@Data
public class UserRegisterRequest {
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
