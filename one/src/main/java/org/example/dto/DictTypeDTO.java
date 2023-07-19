package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.SystemDictData;
import org.example.entity.SystemDictType;

import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Data
@AllArgsConstructor
public class DictTypeDTO {
    private String defaultValue;
    private List<SystemDictData> list;
}
