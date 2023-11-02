package com.haizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haizhi.pojo.Word;

import java.util.List;

public interface WordService extends IService<Word> {

    List<Word> searchWord(String words);

    Word getRandomWord();
}
