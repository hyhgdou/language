package com.haizhi.service.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.SingleChatMapper;
import com.haizhi.pojo.SingleChat;
import com.haizhi.service.SingleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SingleChatServiceImpl extends ServiceImpl<SingleChatMapper, SingleChat> implements SingleChatService {
    @Autowired
    private SingleChatMapper singleChatMapper;


    public SingleChat add(SingleChat singleChat) {
        singleChatMapper.insert(singleChat);//.insertSelective(singleChat)
        return singleChat;
    }
    public List<SingleChat> findByUsername(String fromUser, String toUser) {
        List<SingleChat> list = singleChatMapper.findByUsername(fromUser, toUser);
        list.forEach(x -> {
            if (x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
                x.setReaded(1);
                singleChatMapper.updateById(x);//.updateByPrimaryKey(x)
            }
        });
        return list;
    }

    public Dict findUnReadNums(String toUsername) {
        List<SingleChat> list = singleChatMapper.findByToUsername(toUsername);
        Map<String, List<SingleChat>> collect = list.stream().collect(Collectors.groupingBy(SingleChat::getFromuser));
        Dict dict = Dict.create();
        collect.forEach((k,v) -> dict.set(k, v.size()));
        return dict;
    }



}
