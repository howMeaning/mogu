package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.DictTypeDTO;
import org.example.dto.Result;
import org.example.entity.SystemDictType;

import org.example.service.SystemDictTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@RestController
@RequestMapping("/mogu-web/sysDictData")
@Slf4j
public class DictController {
    @Resource
    private SystemDictTypeService systemDictTypeService;

    @PostMapping("/getListByDictTypeList")
    public Result getListByDictTypeList(
            @RequestBody List<String> list
            ){
        Map<String, DictTypeDTO> listByDictTypeList = systemDictTypeService.getListByDictTypeList(list);
        System.out.println(list);
        log.info("数据:{}",list);
        return Result.ok(listByDictTypeList);
    }
}
