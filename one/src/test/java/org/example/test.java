package org.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.example.entity.Blog;
import org.example.entity.Tag;
import org.example.entity.WebNavBar;
import org.example.mapper.TagMapper;
import org.example.service.BlogService;
import org.example.service.WebNavBarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@SpringBootTest
@Slf4j
public class test {
    @Resource
    private BlogService blogService;
    @Resource
    private WebNavBarService webNavBarService;

    @Resource
    private TagMapper tagMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        log.info("结果:{}",blogService.list());
    }
    @Test
    public void test2(){
        List<WebNavBar> navbar_level = webNavBarService.list(new QueryWrapper<WebNavBar>().eq("navbar_level", 1));
        log.info("结果:{}",navbar_level);

    }
    @Test
    public void text3(){
        int level = 2;
        int useSort = 1;

        LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
      //  new QueryWrapper<>().inSql(, );
       if (useSort == 1) {
           lqw.eq(Blog::getLevel, level).orderByDesc(Blog::getClickCount);
           List<Blog> list = blogService.list(lqw);
           System.out.println(list);
       }else {
           List<Blog> list = blogService.list(lqw);
           System.out.println(list);
       }
    }

    @Test
    public void select(){
        List<Blog> list = blogService.list();
        log.info("{}",list);
    }
    @Test
    public void selectt(){
        ArrayList<String> list = new ArrayList<>();
        list.add("7e0e93ea6cdb44ae92e58f48e6496ed7");
        list.add("1d1fd6d26c8e40a38637ef6126c45cd0");
        List<Tag> list1 = tagMapper.getList(list);
        System.out.println(list1);
    }
    @Test
    public void text(){
        stringRedisTemplate.opsForValue().set("text",new String("text"));
    }
}
