package com.haizhi.controller;


import com.haizhi.pojo.Word;
import com.haizhi.service.IUserService;
import com.haizhi.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/word")
@Api(tags = "字词相关接口")
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/search")
    @ApiOperation(value = "查找指定字词")
    public List<Word> searchVocabulary(@RequestParam("words") String words) {
        return wordService.searchWord(words);
    }

    @GetMapping("/random")
    @ApiOperation(value = "生成随机字词")
    public Word getRandomVocabulary() {
        return wordService.getRandomWord();
    }


}
