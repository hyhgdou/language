package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.Pinyin;
import com.haizhi.pojo.Sentence;
import com.haizhi.pojo.Xiaomin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface XiaominMapper extends BaseMapper<Xiaomin> {
    @Select("SELECT * FROM language.xiaomin")
    List<Xiaomin> findAll();
}
