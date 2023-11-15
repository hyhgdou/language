package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SentenceMapper extends BaseMapper<Sentence> {
    // 自定义查询方法

    @Select("SELECT * FROM language.sentence")
    List<Sentence> findAll();



}
