package com.haizhi.controller;


import com.haizhi.mapper.SentenceMapper;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Sentence;
import com.haizhi.pojo.Word;

import com.haizhi.service.PinyinService;
import com.haizhi.service.SentenceService;
import com.haizhi.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pinyin")
@Api(tags = "拼音相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PinyinController {
    @Autowired
    private PinyinService pinyinService;



    @GetMapping("/random")
    @ApiOperation(value = "生成随机拼音")
    public Pinyin getRandomVocabulary() {
        return pinyinService.getRandomPinyin();
    }


}
