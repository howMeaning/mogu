package org.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.example.dto.*;
import org.example.entity.*;
import org.example.mapper.CommentMapper;
import org.example.service.BlogService;
import org.example.service.CommentService;
import org.example.service.UserService;
import org.example.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.CopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService  {
    @Resource
    private BlogService blogService;
    @Resource
    private UserService userService;
    @Resource
    private CommentMapper commentMapper;
    /*@Override
    public TransferDTO getList(TestDTO testDTO) {
        //根据blogUid找blog表
        Blog blog = blogService.getById(testDTO.getBlogUid());
        //根据blog的userId找user表
        User user = userService.getById(blog.getUserUid());
        String lastLoginIp = user.getLastLoginIp();
        //这里需要将最后的ip转变成具体地址
        String address = IpUtil.getAddress(lastLoginIp);
        //这里需要copy
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        userDTO.setUserIPPossession(address);
        //TODO:开始找comment,根据Blog,这里需要进行分页查询,少了分页
        PageHelper.startPage(testDTO.getCurrentPage(), testDTO.getPageSize());
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("blog_uid", blog.getUid()).and(i -> i.eq("source", testDTO.getSource())));
        PraiseInfo praiseInfo = new PraiseInfo(false,false,(short)0,(short)0);
        CollectInfo collectInfo = new CollectInfo(false, (short) 0);
        //这里赋值
//        CommentDTO<Comment> commentDTO = new CommentDTO<Comment>().setBlog(blog).setUser(userDTO).setReplyList(comments).setPraiseInfo(praiseInfo).setCollectInfo(collectInfo);
        CommentDTO<Comment> commentDTO = new CommentDTO<Comment>().setUserUid(blog.getUserUid()).setContent(blog.getContent())..setUser(userDTO).setReplyList(comments).setPraiseInfo(praiseInfo).setCollectInfo(collectInfo);
        List<CommentDTO<Comment>> commentDTOList = Collections.singletonList(commentDTO);
        TransferDTO<CommentDTO<Comment>> commentDTOTransferDTO = new TransferDTO<>(commentDTOList, commentDTOList.size());
        return commentDTOTransferDTO;
    }*/

    public TransferDTO getList(TestDTO testDTO){
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
//                String ipSource = commentUser.getIpSource();
//                String address = IpUtil.getAddress(ipSource);
                UserDTO commentUserDTO = BeanUtil.copyProperties(commentUser, UserDTO.class); //对应最外部user
//                commentUserDTO.setUserIPPossession(address);

                CommentDTO commentDTO = BeanUtil.copyProperties(comment, CommentDTO.class);
                commentDTO.setUser(commentUserDTO);

                //这里添加commentDTO
                commentDTOS.add(commentDTO);

                //找到第二排
                List<Comment> commentList = commentMapper.selectList(
                        new QueryWrapper<Comment>()
                                .eq("to_user_uid", comment.getUserUid())
                                .and(i -> {
                                    i.eq("blog_uid", comment.getBlogUid());
                                }));

                //递归处理内部
                testCircle(commentList,commentList);

                commentDTO.setReplyList(commentList);
            }
        });
        TransferDTO<CommentDTO> commentDTOTransferDTO = new TransferDTO<>(commentDTOS, commentDTOS.size());
        return commentDTOTransferDTO;
    }
    private void testCircle(List<Comment> commentList,List<Comment> comments) {
        //这个是处理commentList内部
        //拿到所有的评论
        Iterator<Comment> iterator = commentList.iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            String userUid = comment.getUserUid();
            String toUserUid = comment.getToUserUid();
            User user = userService.getById(userUid);
            User toUser = userService.getById(toUserUid);
            UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
            UserDTO toUserDTO = BeanUtil.copyProperties(toUser, UserDTO.class);
            comment.setUser(userDTO);
            comment.setToUser(toUserDTO);
            List<Comment> commentList2 = commentMapper.selectList(
                    new QueryWrapper<Comment>()
                            .eq("to_user_uid", comment.getUserUid())
                            .and(i -> {
                                i.eq("blog_uid", comment.getBlogUid());
                            }));
            //TODO:这里删不全
            commentList2.removeAll(comments);
            if (!CollectionUtil.isEmpty(commentList2)&&comment.getToUser() == null) {
                comments.addAll(commentList2);
                //这里表示还有回复
                comment.setReplyList(commentList2);
                testCircle(commentList2,comments);
            } else {
                //这里就是表示如果没有回复了,直接返回空的replyList
                comment.setReplyList(Collections.emptyList());
            }

        }

    }
    @Override
    public Comment addComment(Comment comment) {
       commentMapper.insert(comment);

        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getUid,comment.getUid());
        Comment com = commentMapper.selectOne(lqw);
        if (com==null){
            return null;
        }
        LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
        ulqw.eq(User::getUid,com.getUserUid());
        User user = userService.getOne(ulqw);
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        comment.setUser(userDTO);
        return comment;
    }
}
