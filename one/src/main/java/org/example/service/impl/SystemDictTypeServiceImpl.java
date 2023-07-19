package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DictTypeDTO;
import org.example.entity.SystemDictData;
import org.example.entity.SystemDictType;

import org.example.mapper.SystemDictDataMapper;
import org.example.mapper.SystemDictTypeMapper;
import org.example.service.SystemDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Service
@Slf4j
public class SystemDictTypeServiceImpl extends ServiceImpl<SystemDictTypeMapper, SystemDictType> implements SystemDictTypeService {
    @Resource
    private SystemDictTypeMapper systemDictTypeMapper;
    @Resource
    private SystemDictDataMapper systemDictDataMapper;
    /**
     * 这里需要三个es查询实际上,这里先用mysql进行模糊匹配
     * @return
     */
    @Override
    public Map<String, DictTypeDTO> getListByDictTypeList(List<String> list) {
        log.info("list:{}",list);
        //先根据list进行in查询
        List<SystemDictType> dict_type = systemDictTypeMapper.selectAllByDictTypes(list);
        List<String> collect = dict_type.stream().map(SystemDictType::getUid).collect(Collectors.toList());
        List<SystemDictData> dict_type_uid = systemDictDataMapper.selectAllByDictTypeUid(collect);
        DictTypeDTO dictTypeDTO = new DictTypeDTO(dict_type_uid.get(0).getDictValue(), dict_type_uid);
        Map<String, DictTypeDTO> stringDictTypeDTOHashMap = new HashMap<>();
        dict_type.forEach(systemDictType -> {
            stringDictTypeDTOHashMap.put(systemDictType.getDictType(),dictTypeDTO);
        });
        log.info("测试:{}",dict_type_uid);

        return stringDictTypeDTOHashMap;
    }
}
