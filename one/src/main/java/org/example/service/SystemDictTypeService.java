package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.DictTypeDTO;
import org.example.entity.SystemDictType;

import java.util.List;
import java.util.Map;


/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/

public interface SystemDictTypeService extends IService<SystemDictType> {
    Map<String, DictTypeDTO> getListByDictTypeList(List<String> list);
}
