package org.example.controller;

import org.example.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@RestController
@RequestMapping("/mogu-web/search")
public class SearchController {
    @GetMapping("getSearchModel")
    public Result getSearchMapper(){
        return  Result.ok("0");
    }
}
