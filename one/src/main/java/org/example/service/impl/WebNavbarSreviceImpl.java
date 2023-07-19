package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.WebNavBar;
import org.example.mapper.WebNavBarMapper;
import org.example.service.WebNavBarService;
import org.springframework.stereotype.Service;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/17
 **/
@Service
public class WebNavbarSreviceImpl extends ServiceImpl<WebNavBarMapper, WebNavBar> implements WebNavBarService {
}
