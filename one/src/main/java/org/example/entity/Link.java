package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Data
@TableName("t_link")
public class Link {
    @TableId
    private String uid;
    private String title;
    private String summary;
    private String url;
    private Integer clickCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Short sort;
    private Short linkStatus;
    private String userUid;
    private String adminUid;
    private String email;
    private String fileUid;
}
