package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //用于登录，核对输入的用户名密码是否正确
    @Select("select *from language.user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);

    //11.25补充：用于注册时：数据库表中是否已经存在该用户名
    @Select("select * from language.user where username = #{username}")
    User getByUsername(String username);

    //新添加：查询所有用户，用于聊天室功能展示聊天用户列表
    @Select("select * from language.user")
    List <User> findALL();

   /*
    @Insert("insert into language.user (account,username,password,phone,sex,identity)values(#{account},#{username},#{password},#{phone},#{sex},#{identity})")
    void addUser(User user);
 */

}
