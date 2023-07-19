package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.BlogDTO;
import org.example.entity.BlogSort;
import org.example.entity.Tag;
import org.example.mapper.BlogMapper;
import org.example.entity.Blog;
import org.example.mapper.BlogSortMapper;
import org.example.mapper.TagMapper;
import org.example.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogSortMapper blogSortMapper;

    @Resource
    private TagMapper tagMapper;
    @Override
    public List<Blog> getListByDictTypeList() {
        return blogMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public BlogDTO getBlogByLevel(Integer level, Integer useSort) {
        //排序功能
        List<Blog> blogOrderList = getBlogOrderList(level, useSort);
        //排序过后
        blogOrderList.forEach(blog-> {
            //三个应该有一个任务分发模式
            String blogSortUid = blog.getBlogSortUid();
            String tagUid = blog.getTagUid();
            String fileUid = blog.getFileUid();
           /* String format = blog.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            String format1 = blog.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            blog.setUpdateTime(LocalDateTime.parse(format));
            blog.setCreateTime(LocalDateTime.parse(format1));*/

                BlogSort blogSort = blogSortMapper.selectOne(new QueryWrapper<BlogSort>().eq("uid",blogSortUid));
                blog.setBlogSort(blogSort);

                List<String> taguidlist = Arrays.stream(tagUid.split(",")).collect(Collectors.toList());
                List<Tag> tagList = tagMapper.getList(taguidlist);
                log.info("测试:{}",tagList);
                blog.setTagList(tagList);

                //这个是别的数据库,需要进行feign的远程调用
                blog.setPhotoList(Collections.emptyList());
        });
        BlogDTO blogDTO = new BlogDTO(blogOrderList, 0, 10, 1, null, true, true);

        return blogDTO;

    }

    @Override
    public BlogDTO getHotBlog() {
        //排序功能
        List<Blog> blogList = blogMapper.selectBlogList();
        //排序过后
        blogList.forEach(blog-> {
            //三个应该有一个任务分发模式
            String blogSortUid = blog.getBlogSortUid();
            String tagUid = blog.getTagUid();
            String fileUid = blog.getFileUid();
           /* String format = blog.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            String format1 = blog.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            blog.setUpdateTime(LocalDateTime.parse(format));
            blog.setCreateTime(LocalDateTime.parse(format1));*/

            BlogSort blogSort = blogSortMapper.selectOne(new QueryWrapper<BlogSort>().eq("uid",blogSortUid));
            blog.setBlogSort(blogSort);

            List<String> taguidlist = Arrays.stream(tagUid.split(",")).collect(Collectors.toList());
            List<Tag> tagList = tagMapper.getList(taguidlist);
            log.info("测试:{}",tagList);
            blog.setTagList(tagList);

            //这个是别的数据库,需要进行feign的远程调用
            blog.setPhotoList(Collections.emptyList());
        });
        BlogDTO blogDTO = new BlogDTO(blogList, 0, 10, 1, null, true, true);
        return blogDTO;
    }

    private List<Blog> getBlogOrderList(Integer level, Integer useSort) {
        LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
        List<Blog> blogList = new ArrayList<>();
        //这里进行排序功能
        if (useSort == 1) {
            lqw.eq(Blog::getLevel, level).orderByDesc(Blog::getClickCount);
             blogList = blogMapper.selectList(lqw);
            return blogList;
        }else {
            blogList = blogMapper.selectList(lqw);
            return blogList;
        }
    }

    public BlogDTO getpage(int page, int pageSize) {
        //进行分页排序
        List<Blog> blogList = blogMapper.selectPage(page, pageSize);
        //排序过后
        blogList.forEach(blog-> {
            //三个应该有一个任务分发模式
            String blogSortUid = blog.getBlogSortUid();
            String tagUid = blog.getTagUid();
            String fileUid = blog.getFileUid();
           /* String format = blog.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            String format1 = blog.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy:MM:DD HH:mm:ss"));
            blog.setUpdateTime(LocalDateTime.parse(format));
            blog.setCreateTime(LocalDateTime.parse(format1));*/

            BlogSort blogSort = blogSortMapper.selectOne(new QueryWrapper<BlogSort>().eq("uid",blogSortUid));
            blog.setBlogSort(blogSort);

            List<String> taguidlist = Arrays.stream(tagUid.split(",")).collect(Collectors.toList());
            List<Tag> tagList = tagMapper.getList(taguidlist);
            log.info("测试:{}",tagList);
            blog.setTagList(tagList);

            //这个是别的数据库,需要进行feign的远程调用
            blog.setPhotoList(Collections.emptyList());
        });
        BlogDTO blogDTO = new BlogDTO(blogList, 0, 10, 1, null, true, true);
        return blogDTO;
    }
}
