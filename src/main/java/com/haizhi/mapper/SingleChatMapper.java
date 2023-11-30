package com.haizhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haizhi.pojo.SingleChat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
@Mapper
public interface SingleChatMapper extends BaseMapper<SingleChat> {


    @Select("select * from language.singlechat where (fromuser = #{fromUser} and touser = #{toUser}) or (fromuser = #{toUser} and touser = #{fromUser})")
    List<SingleChat> findByUsername(String fromUser, String toUser);

    @Select("select * from language.singlechat where touser = #{toUser} and readed = 0")
    List<SingleChat> findByToUsername(String toUser);

    //增加一条回复 回复：在某一条评论下回复，所以可以通过传入外键表示是要在哪一条评论下回复
    //增加一条回复 回复：在某一条评论下回复，所以可以通过传入外键表示是要在哪一条评论下回复

    int insert(SingleChat singleChat);
}
