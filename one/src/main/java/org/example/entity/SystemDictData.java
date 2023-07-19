package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Data
@TableName("t_sys_dict_data")
public class SystemDictData {
    @TableId
    private String uid;
    private Integer oid;
    private String dictTypeUid;
    private String dictLabel;
    private String dictValue;
    private String cssClass;
    private String listClass;
    private Short isDefault;
    private String createByUid;
    private String updateByUid;
    private String remark;
    private Short status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String isPublish;
    private Integer sort;

}
