package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Tag;

import java.util.List;
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> getList(List<String> tag);

    @Select("select * from mogu_blog.t_tag order by click_count desc limit 20")
    List<Tag> getTaglist();
}
