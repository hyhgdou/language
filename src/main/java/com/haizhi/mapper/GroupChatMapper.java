package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.GroupChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupChatMapper extends BaseMapper<GroupChat> {
    List<GroupChat> findBySearch(@Param("search") GroupChat search);


}
