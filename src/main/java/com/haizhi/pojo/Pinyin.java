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
@TableName("pinyin")
public class Pinyin {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String letters;
    private String description;
    private String video;


}
