package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Sentence;
import com.haizhi.pojo.Word;

import java.util.List;

public interface SentenceService extends IService<Sentence> {


    Sentence getRandomSentence();
}
