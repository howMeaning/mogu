package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Blog;

import java.util.List;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> selectByLevel(int level);
    @Select("select * from t_blog order by click_count desc limit 10")
    List<Blog> selectBlogList();

    List<Blog> selectPage(Integer page,Integer pageSize);
}
