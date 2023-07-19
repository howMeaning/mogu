package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.BlogSort;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface BlogSortMapper extends BaseMapper<BlogSort> {

   List<BlogSort> selectByIds(String uid);


}
