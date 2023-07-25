package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class TransferDTO<T> {
    private List<T> records;
    private Integer total;
    private Integer size;       // "size": 10,
    private Integer current;
    private Integer[] orders;
    private boolean optimizeCountSql;
    private boolean isSearchCount;



    public TransferDTO(List<T> records, Integer total) {
        this.records = records;
        this.total = total;
        size = 10;
        current = 1;
        orders = null;
        optimizeCountSql =true;
        isSearchCount = true;
    }
}
