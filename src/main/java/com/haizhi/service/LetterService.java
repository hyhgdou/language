package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Letter;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Sentence;

public interface LetterService extends IService<Letter> {
    Letter getRandomLetter();
}
