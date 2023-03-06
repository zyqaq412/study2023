package com.hzy;

import com.hzy.entity.User;
import com.hzy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @title: com.hzy.test
 * @Author zxwyhzy
 * @Date: 2023/3/5 14:51
 * @Version 1.0
 */
@SpringBootTest
public class test {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user.getName());
        }
    }
    @Test
    public void testQueryList(){
        System.out.println(userMapper.selectList(null));
    }
}
