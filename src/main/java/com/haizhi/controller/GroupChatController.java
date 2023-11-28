package com.haizhi.controller;

import com.haizhi.pojo.GroupChat;
import com.haizhi.pojo.Result;
import com.haizhi.service.GroupChatService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/groupchat")
@Api(tags = "群组聊天相关接口")
public class GroupChatController {
    @Autowired
    private GroupChatService groupChatService;

    /**
     * 描述：新增
     */
    @PostMapping
    public Result add(@RequestBody GroupChat groupChat) {

        groupChatService.add(groupChat);
        return Result.success(groupChat);
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        groupChatService.delete(id);
        return Result.success();
    }

    /**
     * 描述：更新
     */
    @PutMapping
    public Result update(@RequestBody GroupChat groupChat) {

        groupChatService.update(groupChat);
        return Result.success();
    }

    /**
     * 描述：根据ID查询
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        GroupChat groupChat = groupChatService.findById(id);
        return Result.success(groupChat);
    }

    /**
     * 描述：查询所有
     */
    @GetMapping
    public Result all() {

        return Result.success(groupChatService.findAll());
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody GroupChat search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(groupChatService.findPage(search, pageNum, pageSize));
    }
}
