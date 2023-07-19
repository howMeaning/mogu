package org.example.controller;

import org.example.dto.Result;
import org.example.entity.Link;
import org.example.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@RestController
@RequestMapping("/mogu-web/index/")
public class LinkController {
    @Resource
    private LinkService linkService;
    @GetMapping("getLink")
    public Result getLink(){
        List<Link> list = linkService.list();
        return Result.ok(list);
    }
}
