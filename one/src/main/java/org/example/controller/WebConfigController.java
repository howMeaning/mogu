package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dto.Result;
import org.example.entity.Blog;
import org.example.entity.WebNavBar;
import org.example.mapper.BlogMapper;
import org.example.service.BlogService;
import org.example.service.WebConfigService;
import org.example.service.WebNavBarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@RestController
@RequestMapping("/mogu-web/index")
public class WebConfigController {
    @Resource
    private WebConfigService webConfigService;
    @Resource
    private WebNavBarService webNavBarService;
    @Resource
    private BlogService blogService;

    @GetMapping("getWebConfig")
    public Result getWebConfig(){
        return Result.ok(webConfigService.getOne(new QueryWrapper<>()));
    }

    @GetMapping("/getWebNavbar")
    public Result getWebNavbar(@RequestParam("isShow")Integer isShow){
        List<WebNavBar> navbar_level = webNavBarService.list(new QueryWrapper<WebNavBar>().eq("navbar_level", isShow));
        return Result.ok(navbar_level);
    }



   /* @GetMapping("/getNewBlog")
    public Result getNewBlog(@RequestParam Integer currentPage,@RequestParam Integer pageSize){
        return Result.ok();
    }*/

    @GetMapping("/recorderVisitPage")
    public Result recorderVisitPage(){
        return Result.ok("插入成功");
    }
}
