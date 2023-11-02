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
@TableName("word")
public class Word {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String words;
    private String pinyin;
    private String meaning;
    private String example;
    private String video;

}
