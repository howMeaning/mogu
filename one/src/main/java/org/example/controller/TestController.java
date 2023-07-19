package org.example.controller;

import org.example.dto.DictTypeDTO;
import org.example.dto.Result;
import org.example.service.SystemDictTypeService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
    @GetMapping("/getOne")
    public Result test(){
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
}
