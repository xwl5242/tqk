package com.quanchong.dataoke.service.brand.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKBrand;
import com.quanchong.dataoke.mapper.brand.DTKBrandMapper;
import com.quanchong.dataoke.service.brand.DTKBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKBrandServiceImpl extends ServiceImpl<DTKBrandMapper, DTKBrand> implements DTKBrandService {
}
