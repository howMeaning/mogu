package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.example.entity.Blog;
import org.example.entity.CollectInfo;
import org.example.entity.Comment;
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
public class CommentDTO{
        private String uid;
        private Short status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
        //这个就是一个多对一的案例
        //评论人的id
        private String userUid;
        //评论的内容
        private String content;
        //是在哪个博客当中评论
        private String blogUid;
        //前端传
        private String source;
        private Short type;
        //评论人的具体信息
        private UserDTO user;

        private List<Comment> replyList;
}
