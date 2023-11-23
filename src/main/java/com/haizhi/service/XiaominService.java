package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Sentence;
import com.haizhi.pojo.Xiaomin;

public interface XiaominService extends IService<Xiaomin> {
    Xiaomin getRandomXiaomin();
}
