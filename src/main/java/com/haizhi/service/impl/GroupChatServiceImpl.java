package com.haizhi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haizhi.exception.CustomException;
import com.haizhi.mapper.GroupChatMapper;
import com.haizhi.pojo.GroupChat;
import com.haizhi.pojo.User;
import com.haizhi.service.GroupChatService;
import com.haizhi.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupChatServiceImpl extends ServiceImpl<GroupChatMapper, GroupChat> implements GroupChatService {
    @Autowired
    private  GroupChatMapper groupChatMapper;

    public GroupChat add(GroupChat groupChat) {
        groupChatMapper.insert(groupChat);//.insertSelective(imGroup)
        return groupChat;
    }

    public void delete(Integer id) {

        groupChatMapper.deleteById(id);//.deleteByPrimaryKey(id)
    }

    public void update(GroupChat groupChat) {

        groupChatMapper.updateById(groupChat);//.updateByPrimaryKeySelective(groupChat)
    }

    public GroupChat findById(Integer id) {

        return groupChatMapper.selectById(id);//.selectByPrimaryKey(id)
    }

    public List<GroupChat> findAll() {

        return groupChatMapper.findBySearch(null);
    }

    public PageInfo<GroupChat> findPage(GroupChat search, Integer pageNum, Integer pageSize) {
        User user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException("token验证失效");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<GroupChat> all = findByCondition(user, search);

        return PageInfo.of(all);
    }

    public List<GroupChat> findByCondition(User user, GroupChat search) {

        return groupChatMapper.findBySearch(search);
    }

}
