package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Data
@TableName("t_web_config")
public class WebConfig {
    /*
    `uid` varchar(32) NOT NULL COMMENT '主键',
  `logo` varchar(255) NOT NULL COMMENT 'logo(文件UID)',
  `name` varchar(255) NOT NULL COMMENT '网站名称',
  `summary` varchar(255) NOT NULL COMMENT '介绍',
  `keyword` varchar(255) NOT NULL COMMENT '关键字',
  `author` varchar(255) NOT NULL COMMENT '作者',
  `record_num` varchar(255) NOT NULL COMMENT '备案号',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `ali_pay` varchar(32) DEFAULT NULL COMMENT '支付宝收款码FileId',
  `weixin_pay` varchar(32) DEFAULT NULL COMMENT '微信收款码FileId',
  `github` varchar(255) DEFAULT NULL COMMENT 'github地址',
  `gitee` varchar(255) DEFAULT NULL COMMENT 'gitee地址',
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `qq_group` varchar(20) DEFAULT NULL COMMENT 'QQ群',
  `we_chat` varchar(255) DEFAULT NULL COMMENT '微信号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `show_list` varchar(255) DEFAULT NULL COMMENT '显示的列表（用于控制邮箱、QQ、QQ群、Github、Gitee、微信是否显示在前端）',
  `login_type_list` varchar(255) DEFAULT NULL COMMENT '登录方式列表（用于控制前端登录方式，如账号密码,码云,Github,QQ,微信）',
  `open_comment` varchar(1) DEFAULT '1' COMMENT '是否开启评论(0:否 1:是)',
  `open_mobile_comment` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启移动端评论(0:否， 1:是)',
  `open_admiration` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启赞赏(0:否， 1:是)',
  `open_mobile_admiration` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启移动端赞赏(0:否， 1:是)',
  `link_apply_template` varchar(2018) DEFAULT NULL COMMENT '友链申请模板,添加友链申请模板格式',
     */
    @TableId
  private String uid;
  private String logo;
  private String name;
  private String summary;
  private String keyword;
  private String author;
  private String recordNum;
  private short status;
  private LocalDateTime createTime;
  private  LocalDateTime updateTime;
  private String title;
  private String aliPay;
  private String weixinPay;
  private String github;
  private String gitee;
  private String qqGroup;
  private String weChat;
  private String email;
  private String showList;
  private String loginTypeList;
  private String openComment;
  private Short openMobileComment;
  private Short openAdmiration;
  private Short openMobileAdmiration;
  private String linkApplyTemplate;
}
