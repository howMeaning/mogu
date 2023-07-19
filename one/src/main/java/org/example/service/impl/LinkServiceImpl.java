package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Link;
import org.example.mapper.LinkMapper;
import org.example.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/19
 **/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
}
