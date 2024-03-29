package org.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dto.*;
import org.example.entity.Comment;
import org.example.entity.User;
import org.example.mapper.BlogMapper;
import org.example.mapper.CommentMapper;
import org.example.service.SystemDictTypeService;
import org.example.service.UserService;
import org.example.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@RestController
@RequestMapping("Test")
public class TestController {
    @Resource
    private SystemDictTypeService systemDictTypeService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UserService userService;

    @Resource
    private CommentMapper commentMapper;

    @GetMapping("/getOne")
    public Result test() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("sys_yes_no");
        objects.add("sys_user_sex");
        objects.add("sys_feedback_status");
        Map<String, DictTypeDTO> listByDictTypeList = systemDictTypeService.getListByDictTypeList(objects);
        return Result.ok(listByDictTypeList);
    }
    /*@GetMapping("getTwo")
    public Result test(){
        HashMap<"String", > objectObjectHashMap = new HashMap<>();
    }*/

    @PostMapping("/test2")
    public Result test2() {
        TestDTO testDTO = new TestDTO("1207aad62c503ec2881156c846f7a8c3", 1, 10, "BLOG_INFO");
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

        return Result.ok(commentDTOTransferDTO);
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

}
