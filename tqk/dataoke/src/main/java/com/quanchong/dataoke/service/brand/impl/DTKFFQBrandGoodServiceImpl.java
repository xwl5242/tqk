package com.quanchong.dataoke.service.brand.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.ffquan.FFQuanBrandGood;
import com.quanchong.dataoke.mapper.brand.DTKFFQBrandGoodMapper;
import com.quanchong.dataoke.service.brand.DTKFFQBrandGoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKFFQBrandGoodServiceImpl extends ServiceImpl<DTKFFQBrandGoodMapper, FFQuanBrandGood>
        implements DTKFFQBrandGoodService {
}
