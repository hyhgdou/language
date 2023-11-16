package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.LetterMapper;
import com.haizhi.pojo.Letter;
import com.haizhi.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class LetterServiceImpl extends ServiceImpl<LetterMapper, Letter> implements LetterService {
    @Autowired
    private LetterMapper letterMapper;

    @Override
    public Letter getRandomLetter() {
        List<Letter> allLetter = letterMapper.findAll();
        int randomIndex = new Random().nextInt(allLetter.size());
        return allLetter.get(randomIndex);
    }


}
