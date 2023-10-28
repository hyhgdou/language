package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.haizhi.mapper.UserMapper;
import com.haizhi.pojo.User;
import com.haizhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
      //  String password = user.getPassword();
       // DigestUtils.md5DigestAsHex(password.getBytes());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.getByUsernameAndPassword(user);
    }
}

