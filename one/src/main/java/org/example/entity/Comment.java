package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.dto.UserDTO;
import org.example.entity.CollectInfo;
import org.example.entity.PraiseInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Data
@Accessors(chain = true)
@TableName("t_comment")
public class Comment {
    private String userUid;
    private String toUid;
    private String firstCommentUid;
    private String toUserUid;
    private String content;
    private String blogUid;
    private String source;
    private Short type;
    @TableField(exist = false)
    private UserDTO user;
    @TableField(exist = false)
    private UserDTO toUser;
    @TableField(exist = false)
    private List<Comment> replyList;

    private String uid;
    private Short status;
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime updateTime;
}
