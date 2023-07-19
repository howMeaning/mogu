package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.example.entity.Tag;
import org.example.service.BlogService;
import org.example.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/mogu-web/index/")
public class HotController {
    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;
    @GetMapping("/getHotTag")
    public Result getTagList(){
        List<Tag> tageList = tagService.getTagList();
        return Result.ok(tageList);
    }
    @GetMapping("/getHotBlog")
    public Result getHotBlog(){
       return  Result.ok(blogService.getHotBlog());
    }



}
