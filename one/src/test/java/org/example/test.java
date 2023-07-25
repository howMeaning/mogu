package org.example;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.entity.*;
import org.example.mapper.BlogMapper;
import org.example.mapper.CommentMapper;
import org.example.mapper.TagMapper;
import org.example.service.BlogService;
import org.example.service.CommentService;
import org.example.service.UserService;
import org.example.service.WebNavBarService;
import org.example.util.IpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UserService userService;

    @Resource
    private CommentMapper commentMapper;

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

    @Test
    public void testIp(){
        String address = IpUtil.getAddress("120.229.174.32");
        System.out.println("结果:"+address);
    }

    @Test
    public void testPage(){
        PageHelper.startPage(0, 5);
        List<Blog> list = blogService.list();
        System.out.println(list);
    }
    @Test
    public void testPage2(){
        PageHelper.startPage(1,5);
        List<Blog> blogList1 = blogMapper.selectList(new QueryWrapper<>());
        System.out.println(blogList1.size());
        /*Page<Blog> page = new Page<>(0,5);
        Page<Blog> page1 = blogMapper.selectPage(page, null);
        System.out.println(page1);*/
    }
    //TODO:这里应该有一个递归操作  递归思路:如果查到有UserUid,则继续递归,如果无UserUid
    @Test
    public void testUser(){
        TestDTO testDTO = new TestDTO("1207aad62c503ec2881156c846f7a8c3",1,10,"BLOG_INFO");
        String blogUid = testDTO.getBlogUid();
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("blog_uid", blogUid));
        List<CommentDTO> commentDTOS = new ArrayList<>(comments.size());
        comments.forEach(comment -> {
                    String toUserUid = comment.getToUserUid();

                    if (StrUtil.isBlank(toUserUid)) {
                        //如果toUserUid为空说明当前的comment就是前排

                        String userUid = comment.getUserUid();
                        //评论的用户
                        User commentUser = userService.getById(userUid);
                        String ipSource = commentUser.getIpSource();
                        String address = IpUtil.getAddress(ipSource);
                        UserDTO commentUserDTO = BeanUtil.copyProperties(commentUser, UserDTO.class); //对应最外部user
//                        commentUserDTO.setUserIPPossession(address);

                        CommentDTO commentDTO = BeanUtil.copyProperties(comment, CommentDTO.class);
                        commentDTO.setUser(commentUserDTO);

                        //这里添加commentDTO
                        commentDTOS.add(commentDTO);

                        //找到第二排
                        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("to_user_id", comment.getUserUid()));

                        //递归处理内部
                        testCircle(commentList);

                        commentDTO.setReplyList(commentList);
                    }
                })  ;
                        TransferDTO<CommentDTO> commentDTOTransferDTO = new TransferDTO<>(commentDTOS, commentDTOS.size());
                       System.out.println(commentDTOTransferDTO);

    }
    private  void testCircle(List<Comment> commentList){
        //这个是处理commentList内部
        //拿到所有的评论
        for (Comment comment : commentList) {
            List<Comment> commentList2 = commentMapper.selectList(new QueryWrapper<Comment>().eq("to_user_id", comment.getUserUid()));
            if (!CollectionUtil.isEmpty(commentList2)){
                //这里表示还有回复
                String userUid = comment.getUserUid();
                String toUserUid = comment.getToUserUid();
                User user = userService.getById(userUid);
                User toUser = userService.getById(toUserUid);
                UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
                UserDTO toUserDTO = BeanUtil.copyProperties(toUser, UserDTO.class);
                comment.setUser(userDTO);
                comment.setToUser(toUserDTO);
                testCircle(commentList2);
            }else{
                //这里就是表示如果没有回复了,直接返回空的replyList
                comment.setReplyList(Collections.emptyList());
            }

        }
    }

    /*//这里需要根据blogUid找t_comment
    List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("blog_uid", blogUid));
    List<Comment> replyList = new ArrayList<>(commentList.size());
    //这里应该有多少个Comment
    List<CommentDTO> commentDTOs = new ArrayList<>(commentList.size());
    //这里从comment中拿到user的id;
        commentList.forEach(comment -> {
        //这里需要根据user查user表,外部评论的账号信息
        User user = userService.getById(comment.getUserUid());
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        CommentDTO commentDTO = BeanUtil.copyProperties(comment, CommentDTO.class);

        //这个是回复评论的userid
        String toUid = comment.getToUid();
        //这里需要找具体的信息
        String toUserUid = comment.getToUserUid();
        User byId = userService.getById(toUid);
        User byId1 = userService.getById(toUserUid);
        UserDTO userDTO1 = BeanUtil.copyProperties(byId, UserDTO.class);
        UserDTO userDTO2 = BeanUtil.copyProperties(byId1, UserDTO.class);
        //这里就是最外部评论用户的具体信息的传递
        comment.setToUser(userDTO1);
        comment.setUser(userDTO2);

        commentDTO.setUser(userDTO);
        replyList.add(comment);
        commentDTOs.add(commentDTO);
    });

    TransferDTO<CommentDTO> commentDTOTransferDTO = new TransferDTO<>(commentDTOs, commentDTOs.size());
        commentDTOTransferDTO.setRecords(commentDTOs);
        System.out.println(commentDTOTransferDTO);*/


    @Test
    public void recursion(){

    }

}
