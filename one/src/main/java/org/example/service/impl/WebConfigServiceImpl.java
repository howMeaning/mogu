package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.WebConfigMapper;
import org.example.entity.WebConfig;
import org.example.service.WebConfigService;
import org.springframework.stereotype.Service;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Service
public class WebConfigServiceImpl extends ServiceImpl<WebConfigMapper, WebConfig> implements WebConfigService {
}
