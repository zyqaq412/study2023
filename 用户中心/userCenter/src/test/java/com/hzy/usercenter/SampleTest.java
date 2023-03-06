package com.hzy.usercenter;

import com.hzy.usercenter.entity.User;
import com.hzy.usercenter.mapper.UserMapper;
import com.hzy.usercenter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: SampleTest
 * @Author zxwyhzy
 * @Date: 2023/3/5 13:58
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class SampleTest {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private UserService userService;



    @Test
    public void testRegister(){
        long zxwy = userService.userRegister("zxwy", "123456", "123456");
        System.out.println(zxwy);
    }
    @Test
    public void testRemove(){
        //userService.removeById(1);
        userService.removeById(1);
    }
    @Test
    public void ins(){
        User user = new User();
        user.setId(2L);
        user.setUsername("zyo_O");
        user.setUseraccount("zxwyhzy");
        user.setAvatarurl("www");
        user.setGender(1);
        user.setUserpassword("120125hzy.");
        user.setPhone("17623247012");
        user.setEmail("123qq.com");

        userService.save(user);
        System.out.println(userService.getById(1));
    }
    @Test
    public void testSelect2(){
        List<User> list = userService.list();
        list.forEach(user -> System.out.println(user.getUsername()));
    }

    @Test
    public void testSelect1(){
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }
}
