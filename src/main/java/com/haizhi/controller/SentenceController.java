package com.haizhi.controller;


import com.haizhi.pojo.Sentence;
import com.haizhi.service.SentenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/sentence")
@Api(tags = "诗句相关接口")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;



    @GetMapping("/random")
    @ApiOperation(value = "生成随机诗句")
    public Sentence getRandomVocabulary() {
        return sentenceService.getRandomSentence();
    }


}
