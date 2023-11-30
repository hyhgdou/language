package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.haizhi.exception.CustomException;
import com.haizhi.mapper.UserMapper;
import com.haizhi.pojo.User;
import com.haizhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //登录
    @Override
    public User login(User user) {
      //  String password = user.getPassword();
       // DigestUtils.md5DigestAsHex(password.getBytes());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        User user1=  userMapper.getByUsername(user.getUsername());
        user.setIdentity(user1.getIdentity());
        return userMapper.getByUsernameAndPassword(user);
    }

    @Override
    public List<User> findALL() {

        return userMapper.findALL();
    }

    public User findById(Integer id){

        return userMapper.selectById(id);
    }

    //用于用户注册
    public String addUser(User user) {
        String msg="success";
        //注册时用户填写的信息顺序：account,username,password,phone
        //1.用户注册时首先要核验是否已经存在该username
        if(userMapper.getByUsername(user.getUsername())!=null){//能够获取到返回值，说明数据库表中存在有username的信息，重复不予以注册
            msg="您输入的用户名已被他人使用，请重新输入！";
            //throw new CustomException("您输入的用户名已被他人使用，请重新输入！");
        }else {
            //2.判断注册的username是否合法：6位的英文字母
            //2.1判断长度
            if(user.getUsername().length()<20){
                //2.2判断是否是英文字母(包含大小写)--遍历字符
           //     for(int i=0;i<user.getUsername().length();i++){
              //      char element=user.getUsername().charAt(i);
               //   if((element >='a' && element <= 'z') || (element >= 'A' && element <= 'Z' )){//符合条件
                        //3.判断输入的account是否超过20位
                        if(user.getAccount().length()<=20 && user.getAccount().length() > 0){
                            //4.判断输入的密码password是否超过20位且不能为空
                            if(user.getPassword().length()<=20 && user.getPassword().length()>0){
                                //5.判断输入的电话号码是否符合规则：是数字且等于11位
                                if(user.getPhone().length()==11){
                                    String pp = user.getPassword();
                                    String s = DigestUtils.md5DigestAsHex(pp.getBytes());
                                    user.setPassword(s);
                                    userMapper.insert(user);
                                }else {
                                    msg="请输入正确的手机号码！";
                                    //throw new CustomException("请输入正确的手机号码！");
                                }
                            }else{
                                msg="您设置的密码不符合要求！请重新输入！";
                                //throw new CustomException("您设置的密码过长！请重新输入！");
                            }
                        }else{
                            msg="您输入的昵称不符合要求！请重新输入！";
                            //throw new CustomException("您输入的昵称过长！请重新输入！");
                        }
                //    }else{//不符合条件
                    //    msg="您输入的密码不符合要求，请重新输入！";
                        //throw new CustomException("您输入的密码不符合要求，请重新输入！");
               //     }
              //  }
            }else {
                msg="您输出的用户名不符合要求，请重新输入！";
            }
        }
        return  msg;
    }
}

