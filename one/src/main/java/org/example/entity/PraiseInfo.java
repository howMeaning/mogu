package org.example.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Data
@AllArgsConstructor
public class PraiseInfo {
    private Boolean isTread;
    private Boolean isPraise;
    private Short treadCount;
    private Short praiseCount;
}
