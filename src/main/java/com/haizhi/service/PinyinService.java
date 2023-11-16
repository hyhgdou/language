package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Sentence;

public interface PinyinService extends IService<Pinyin> {
    Pinyin getRandomPinyin();
}
