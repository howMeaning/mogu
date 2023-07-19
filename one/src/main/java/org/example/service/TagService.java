package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> getTagList();
}
