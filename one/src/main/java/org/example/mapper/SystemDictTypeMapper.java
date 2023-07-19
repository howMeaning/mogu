package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.SystemDictType;

import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Mapper
public interface SystemDictTypeMapper extends BaseMapper<SystemDictType> {

    List<SystemDictType> selectAllByDictTypes(List<String> list);
}
