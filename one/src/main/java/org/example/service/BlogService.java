package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.Result;
import org.example.dto.TransferDTO;
import org.example.entity.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    List<Blog> getListByDictTypeList();

    TransferDTO getBlogByLevel(Integer level, Integer useSort);

    TransferDTO getHotBlog();

    TransferDTO getpage(int page, int pageSize);

    TransferDTO getSameBlogByBlogUid(String blogUid);

    Blog getBlogByUid(Integer oid);
}
