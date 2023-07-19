package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_blog")
public class Blog {
    /*`uid` varchar(32) NOT NULL COMMENT '唯一uid',
  `title` varchar(200) DEFAULT NULL COMMENT '博客标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '博客简介',
  `content` longtext COMMENT '博客内容',
  `tag_uid` varchar(255) DEFAULT NULL COMMENT '标签uid',
  `click_count` int DEFAULT '0' COMMENT '博客点击数',
  `collect_count` int DEFAULT '0' COMMENT '博客收藏数',
  `file_uid` varchar(255) DEFAULT NULL COMMENT '标题图片uid',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `admin_uid` varchar(32) DEFAULT NULL COMMENT '管理员uid',
  `is_original` varchar(1) DEFAULT '1' COMMENT '是否原创（0:不是 1：是）',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `articles_part` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `blog_sort_uid` varchar(32) DEFAULT NULL COMMENT '博客分类UID',
  `level` tinyint(1) DEFAULT '0' COMMENT '推荐等级(0:正常)',
  `is_publish` varchar(1) DEFAULT '1' COMMENT '是否发布：0：否，1：是',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序字段',
  `open_comment` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开启评论(0:否 1:是)',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型【0 博客， 1：推广】',
  `outside_link` varchar(1024) DEFAULT NULL COMMENT '外链【如果是推广，那么将跳转到外链】',
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '唯一oid',
  `user_uid` varchar(32) DEFAULT NULL COMMENT '投稿用户UID',
  `article_source` tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章来源【0 后台添加，1 用户投稿】',*/
    //唯一uid
    @TableId()
    private String uid;
    //博客标题
    private String title;
    //博客简介
    private String summary;
    //博客内容
    private String content;
    //标签uid
    private String tagUid;
    //博客点击数
    private Integer clickCount;
    //博客收藏数
    private Integer collectCount;
    //标题图片uid
    private String fileUid;
    //状态
    private Short status;
    //状态 DEFAULT '0000-00-00 00:00:00'
    private LocalDateTime createTime;
    //创建时间 DEFAULT '0000-00-00 00:00:00'
    private LocalDateTime updateTime;
    //管理员uid
    private String adminUid;
    //是否原创
    private String isOriginal;
    //作者
    private String author;
    //文章出处
    private String articlesPart;
    //博客分类UID
    private String blogSortUid;
    //推荐等级(0:正常)
    private Short level;
    //是否发布：0：否，1：是
    private String isPublish;
    //排序字段
    private Integer sort;
    //是否开启评论(0:否 1:是)
    private Short openComment;
    //类型【0 博客， 1：推广】
    private Short type;
    //外链【如果是推广，那么将跳转到外链】
    private String outsideLink;
    //唯一oid
    private Integer oid;
    //投稿用户UID
    private String userUid;
    //文章来源【0 后台添加，1 用户投稿】
    private Short articleSource;
    //下个都需要连表查询,不需要从数据库当中找
    @TableField(exist = false)
    private BlogSort blogSort;
    @TableField(exist = false)
    private List<String> photoList;
    @TableField(exist = false)
    private List<Tag> tagList;
}
