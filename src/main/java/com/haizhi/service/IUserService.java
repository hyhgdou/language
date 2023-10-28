package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.User;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;

public interface IUserService extends IService<User> {
    User login(User user) throws AccountNotFoundException, AccountLockedException;

}
