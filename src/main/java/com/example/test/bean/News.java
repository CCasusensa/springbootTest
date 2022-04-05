package com.example.test.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("news")
public class News {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String context;
    private String author;
    private String createTime;
}
