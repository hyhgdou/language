package com.haizhi.service;


import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.SingleChat;


import java.util.List;


public interface SingleChatService extends IService<SingleChat> {

    //添加
    SingleChat add(SingleChat singleChat);

    //查找1
    List <SingleChat> findByUsername(String fromUser,String toUser);

    //查找2
    Dict findUnReadNums(String toUsername);


}
