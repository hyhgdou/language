package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Letter;

public interface LetterService extends IService<Letter> {
    Letter getRandomLetter();
}
