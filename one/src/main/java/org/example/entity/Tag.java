package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@Data
@TableName("t_tag")
public class Tag {
    @TableId
    private String uid;
    private String content;
    private Integer status;
    private Integer clickCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer sort;


}
