package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@TableName("t_blog_sort")
@Data
@Getter
@Setter
public class BlogSort {
    /*`uid` varchar(32) NOT NULL COMMENT '唯一uid',
  `sort_name` varchar(255) DEFAULT NULL COMMENT '分类内容',
  `content` varchar(255) DEFAULT NULL COMMENT '分类简介',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `sort` int DEFAULT '0' COMMENT '排序字段，越大越靠前',
  `click_count` int DEFAULT '0' COMMENT '点击数',*/
    //唯一uid
    @TableId
    private String uid;
    private String sortName;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Short status;
    private Integer sort;
    private Integer clickCount;

}
