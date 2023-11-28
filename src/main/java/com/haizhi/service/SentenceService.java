package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Sentence;


public interface SentenceService extends IService<Sentence> {


    Sentence getRandomSentence();
}
