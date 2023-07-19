package org.example.controller;

import org.example.dto.BlogDTO;
import org.example.dto.Result;
import org.example.entity.Blog;
import org.example.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@RestController
@RequestMapping("/mogu-web/index")
public class BlogController {

    @Resource
    private  BlogService blogService;
   /* @Resource
    private BlogService blogService;
    @PostMapping("/getListByDictTypeList")
    public Result getListByDictTypeList(){
        return Result.ok(blogService.getListByDictTypeList());
    }*/

    @GetMapping("/getNewBlog")
    public Result blogPage(@RequestParam Integer currentPage , @RequestParam Integer pageSize){
        BlogDTO getpage = blogService.getpage(currentPage, pageSize);

        return Result.ok(getpage);
    }
}
