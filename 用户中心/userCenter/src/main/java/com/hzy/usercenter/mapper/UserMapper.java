package com.hzy.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.usercenter.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-05 13:47:27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
