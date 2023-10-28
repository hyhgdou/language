package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select *from language.user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);
    @Select("select * from language.user where username = #{username}")
    User getByUsername(String username);
}
