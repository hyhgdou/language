package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.SentenceMapper;
import com.haizhi.pojo.Sentence;
import com.haizhi.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper,Sentence> implements SentenceService {
    @Autowired
    private SentenceMapper sentenceMapper;

    @Override
    public Sentence getRandomSentence() {
        List<Sentence> allSentence = sentenceMapper.findAll();
        int randomIndex = new Random().nextInt(allSentence.size());
        return allSentence.get(randomIndex);
    }


}
