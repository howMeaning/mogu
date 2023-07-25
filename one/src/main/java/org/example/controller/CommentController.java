package org.example.controller;

import org.example.dto.Result;
import org.example.dto.TestDTO;
import org.example.entity.Comment;
import org.example.service.CommentService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@RestController
@RequestMapping("/mogu-web/web/comment/")
public class CommentController {
    @Resource
    private CommentService commentService;
    @PostMapping("getList")
    public Result getList(@RequestBody TestDTO testDTO){
        return Result.ok(commentService.getList(testDTO));
    }


    @PostMapping("add")
    public Result commentAdd(@RequestBody Comment comment){
        //不清楚uid前端会不会传值，可能会因为uid报错，需要自定义uid
        Comment comment1 = commentService.addComment(comment);
        return Result.ok(comment1);
    }
}
