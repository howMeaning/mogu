package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Data
@TableName("t_web_navbar")
public class WebNavBar {
    @TableId
    private String uid;
    private String name;
    private Short navbarLevel;
    private String summary;
    private String parentUid;
    private String url;
    private String icon;
    private Short isShow;
    private Short isJumpExternalUrl;
    private Integer sort;
    private Short status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
