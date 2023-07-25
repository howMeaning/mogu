package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dto.TransferDTO;
import org.example.dto.Result;
import org.example.entity.Blog;
import org.example.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@RestController
@RequestMapping("/mogu-web")
public class BlogController {

    @Resource
    private  BlogService blogService;
   /* @Resource
    private BlogService blogService;
    @PostMapping("/getListByDictTypeList")
    public Result getListByDictTypeList(){
        return Result.ok(blogService.getListByDictTypeList());
    }*/

    @GetMapping("/index/getNewBlog")
    public Result blogPage(@RequestParam Integer currentPage , @RequestParam Integer pageSize){
        TransferDTO getpage = blogService.getpage(currentPage, pageSize);

        return Result.ok(getpage);
    }
    @GetMapping("/index/getBlogByLevel")
    public Result getBlogByLevel(@RequestParam Integer level,@RequestParam Integer useSort){

        return Result.ok(blogService.getBlogByLevel(level,useSort));
    }

    @GetMapping("/content/getBlogByUid")
    public Result getBlogByUid(@RequestParam("oid") Integer oid){


        return Result.ok(blogService.getBlogByUid(oid));
    }

    @GetMapping("/content/getSameBlogByBlogUid")
    public Result getSameBlogByBlogUid(@RequestParam("blogUid") String blogUid){
        return Result.ok(blogService.getSameBlogByBlogUid(blogUid));
    }


}
