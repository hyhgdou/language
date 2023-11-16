package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.Letter;
import com.haizhi.pojo.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LetterMapper extends BaseMapper<Letter> {
    @Select("SELECT * FROM language.letter")
    List<Letter> findAll();
}
