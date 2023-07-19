package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.Blog;

import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/18
 **/
@Data
@AllArgsConstructor
public class BlogDTO {
    private List<Blog> records;
    private Integer total;
    private Integer size;       // "size": 10,
    private Integer current;
    private Integer[] orders;
    private boolean optimizeCountSql;
    private boolean isSearchCount;
}
