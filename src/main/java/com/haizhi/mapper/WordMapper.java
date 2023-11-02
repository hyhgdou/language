package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<Word> {
    // 自定义查询方法
    @Select("SELECT * FROM language.word WHERE words = #{words}")
    List<Word> findByWord(String words);
    @Select("SELECT * FROM language.word")
    List<Word> findAll();







}
