package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.BlogDTO;
import org.example.entity.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    List<Blog> getListByDictTypeList();

    BlogDTO getBlogByLevel(Integer level, Integer useSort);

    BlogDTO getHotBlog();

    BlogDTO getpage(int page, int pageSize);
}
