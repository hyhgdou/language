package com.haizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haizhi.mapper.LetterMapper;
import com.haizhi.mapper.PinyinMapper;
import com.haizhi.mapper.XiaominMapper;
import com.haizhi.pojo.Letter;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Xiaomin;
import com.haizhi.service.LetterService;
import com.haizhi.service.PinyinService;
import com.haizhi.service.XiaominService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class XiaominServiceImpl extends ServiceImpl<XiaominMapper, Xiaomin> implements XiaominService {
    @Autowired
    private XiaominMapper xiaominMapper;

    @Override
    public Xiaomin getRandomXiaomin() {
        List<Xiaomin> allXiaomin = xiaominMapper.findAll();
        int randomIndex = new Random().nextInt(allXiaomin.size());

        return allXiaomin.get(randomIndex);
    }


}
