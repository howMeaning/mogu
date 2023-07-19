package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.SystemDictData;

import java.util.List;

public interface SystemDictDataMapper extends BaseMapper<SystemDictData> {
    List<SystemDictData> selectAllByDictTypeUid(List<String> list);

}
