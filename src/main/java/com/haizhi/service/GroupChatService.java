package com.haizhi.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.haizhi.pojo.GroupChat;
import com.haizhi.pojo.User;


import java.util.List;

public interface GroupChatService extends IService<GroupChat> {

    GroupChat add(GroupChat groupChat);

    void delete(Integer id);

    void update(GroupChat groupChat);

    GroupChat findById(Integer id);

    List<GroupChat> findAll();
    PageInfo<GroupChat> findPage(GroupChat search, Integer pageNum, Integer pageSize);

    List<GroupChat> findByCondition(User user, GroupChat search);

}
