package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Data
@TableName("t_user")
public class User {
    @TableId
    private String uid;
    private String userName;
    private String passWord;
    private Integer gender;
    private String avatar;
    private String email;
    private LocalDateTime birthday;
    private String mobile;
    private String validCode;
    private String summary;//自我简介
    private Integer loginCount;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String nickName;
    private String source;
    private String uuid;
    private String qqNumber;
    private String weChat;
    private String occupation;
    private Integer commentStatus;
    private String ipSource;
    private String browser;
    private String os;
    private Integer startEmailNotification;
    private Integer userTag;
    private Integer loadingValid;

}
