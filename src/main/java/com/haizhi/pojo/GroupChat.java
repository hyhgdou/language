package com.haizhi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("groupchat")
public class GroupChat {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;//主键

    private String content;//内容

    private String name;//用户名

    private String avatar;//头像

    private String time;//时间

    private String type;//类型

}
