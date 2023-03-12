package com.hzy.usercenter.entity.request;

import lombok.Data;

/**
 * @title: UserLoginRequest
 * @Author zxwyhzy
 * @Date: 2023/3/12 13:15
 * @Version 1.0
 */
@Data
public class UserLoginRequest {
    private String userAccount;
    private String userPassword;
}
