package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.dataoke.entity.DTKUser;
import com.quanchong.dataoke.mapper.DTKUserMapper;
import com.quanchong.dataoke.service.DTKUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKUserServiceImpl extends ServiceImpl<DTKUserMapper, DTKUser> implements DTKUserService {
}
