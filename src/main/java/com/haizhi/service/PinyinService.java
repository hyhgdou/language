package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Pinyin;

public interface PinyinService extends IService<Pinyin> {
    Pinyin getRandomPinyin();
}
