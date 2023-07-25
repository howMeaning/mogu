package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/21
 **/
@Data
@Accessors(chain = true)
@Setter
@Getter
public class UserDTO {
    private String uid;
    private String nickName;
    private String avatar;
    private Short userTag;
//    private Short loadingVaild;
//    private Integer credits;
    private String photoUrl;
   /* private Short userLevel;
    private String userIPPossession;*/
    private Short status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
