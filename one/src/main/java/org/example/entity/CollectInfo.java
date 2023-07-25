package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Data
@AllArgsConstructor
public class CollectInfo {
    private Boolean isCollect;
    private Short collectCount;

}
