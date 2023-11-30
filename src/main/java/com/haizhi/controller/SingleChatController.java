package com.haizhi.controller;


import cn.hutool.core.lang.Dict;
import com.haizhi.pojo.Result;
import com.haizhi.pojo.SingleChat;
import com.haizhi.pojo.User;
import com.haizhi.service.SingleChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/singlechat")
@Api(tags = "单人聊天相关接口")
public class SingleChatController {

    @Autowired
    private SingleChatService singleChatService;

    /**
     * 查询所有消息
     */
    @GetMapping
    public Result findByFromUsername(@RequestParam String fromUser, @RequestParam String toUser) {

        List<SingleChat> all = singleChatService.findByUsername(fromUser, toUser);
        return Result.success(all);
    }

    /**
     * 查询未读消息数量
     * @return 未读消息数量
     */
    @GetMapping("/unReadNums")
    public Result findUnReadNums(@RequestParam String toUsername) {

        Dict dict = singleChatService.findUnReadNums(toUsername);
        return Result.success(dict);
    }
/*
* 测试添加单人聊天信息功能
*  @PostMapping("/addMessage")
    @ApiOperation(value = "添加单人聊天消息")
    public Result save(@RequestBody SingleChat singleChat) {
        log.info("聊天,singlechat:{}",singleChat);
        singleChatService.add(singleChat);
            return Result.success();

    }
* */


}
