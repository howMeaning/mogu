package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.dto.Result;
import org.example.dto.TransferDTO;
import org.example.entity.Blog;
import org.example.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@RestController
@RequestMapping("/mogu-web/search")
public class SearchController {

    @Resource
    private BlogService blogService;
    @GetMapping("getSearchModel")
    public Result getSearchMapper(){
        return  Result.ok("0");
    }

    @GetMapping("/sqlSearchBlog")//currentPage,pageSize,keywords
    public Result searchBlog(@RequestParam Integer currentPage,
                             @RequestParam Integer pageSize, @RequestParam String keywords){
        TransferDTO<Blog> transferDTO = new TransferDTO();
        transferDTO.setSize(pageSize).setCurrent(currentPage);
        LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
        lqw.like(Blog::getTitle,keywords);
        List<Blog> blogs = blogService.list(lqw);
        transferDTO.setRecords(blogs).setTotal(blogs.size());

        return Result.ok(transferDTO);
    }
}
