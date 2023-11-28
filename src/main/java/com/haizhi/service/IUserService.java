package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.User;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface IUserService extends IService<User> {
    User login(User user) throws AccountNotFoundException, AccountLockedException;

    //新添加：查询所有用户
    List<User> findALL();

    String addUser(User user);
}
