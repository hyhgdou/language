package com.haizhi.controller;


import com.haizhi.mapper.SentenceMapper;
import com.haizhi.pojo.Sentence;
import com.haizhi.pojo.Word;

import com.haizhi.service.SentenceService;
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
@RequestMapping("/sentence")
@Api(tags = "诗句相关接口")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;



    @GetMapping("/random")
    @ApiOperation(value = "生成随机诗句")
    public Sentence getRandomVocabulary() {
        return sentenceService.getRandomSentence();
    }


}
