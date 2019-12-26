package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKBrand;
import com.quanchong.dataoke.mapper.DTKBrandMapper;
import com.quanchong.dataoke.service.DTKBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKBrandServiceImpl extends ServiceImpl<DTKBrandMapper, DTKBrand> implements DTKBrandService {
}
