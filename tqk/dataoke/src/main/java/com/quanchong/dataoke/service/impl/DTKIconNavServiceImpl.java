package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKIconNav;
import com.quanchong.dataoke.mapper.DTKIconNavMapper;
import com.quanchong.dataoke.service.DTKIconNavService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKIconNavServiceImpl extends ServiceImpl<DTKIconNavMapper, DTKIconNav> implements DTKIconNavService {
}
