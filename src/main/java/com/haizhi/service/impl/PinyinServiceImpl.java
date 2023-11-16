package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.LetterMapper;
import com.haizhi.mapper.PinyinMapper;
import com.haizhi.pojo.Letter;
import com.haizhi.pojo.Pinyin;
import com.haizhi.service.LetterService;
import com.haizhi.service.PinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class PinyinServiceImpl extends ServiceImpl<PinyinMapper, Pinyin> implements PinyinService {
    @Autowired
    private PinyinMapper pinyinMapper;

    @Override
    public Pinyin getRandomPinyin() {
        List<Pinyin> allPinyin = pinyinMapper.findAll();
        int randomIndex = new Random().nextInt(allPinyin.size());
        return allPinyin.get(randomIndex);
    }


}
