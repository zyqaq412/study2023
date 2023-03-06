package com.hzy.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.usercenter.entity.User;
import com.hzy.usercenter.mapper.UserMapper;
import com.hzy.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @title: UserServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/3/5 16:26
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
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
            final String SALT = "zy";
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
}
