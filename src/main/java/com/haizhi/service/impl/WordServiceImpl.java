package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.UserMapper;
import com.haizhi.mapper.WordMapper;
import com.haizhi.pojo.User;
import com.haizhi.pojo.Word;
import com.haizhi.service.IUserService;
import com.haizhi.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {
    @Autowired
    private WordMapper wordMapper;
    @Override
    public List<Word> searchWord(String words) {
        return wordMapper.findByWord(words);
    }

    @Override
    public Word getRandomWord() {
        List<Word> allWord = wordMapper.findAll();
        int randomIndex = new Random().nextInt(allWord.size());
        return allWord.get(randomIndex);
    }
}
