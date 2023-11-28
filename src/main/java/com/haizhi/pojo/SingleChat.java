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
@TableName("singlechat")
public class SingleChat {

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;//主键自增长

    private String content;//消息内容

    private String fromuser;//发送人

    private String fromavatar;//发送人头像

    private String time;//时间

    private String type;//消息类型

    private String touser;//接收人

    private String toavatar;//接收人头像

    private Integer readed;//是否已读

}
