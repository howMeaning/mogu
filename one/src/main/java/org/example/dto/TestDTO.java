package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/22
 **/
@Data
@AllArgsConstructor
public class TestDTO {
    private String blogUid;
   private Integer currentPage;
   private Integer pageSize;
    private String source;
}
