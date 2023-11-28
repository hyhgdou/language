package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.Pinyin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PinyinMapper extends BaseMapper<Pinyin> {
    @Select("SELECT * FROM language.pinyin")
    List<Pinyin> findAll();
}
