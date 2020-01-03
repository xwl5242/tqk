package com.quanchong.dataoke.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKUser;
import com.quanchong.dataoke.mapper.sys.DTKUserMapper;
import com.quanchong.dataoke.service.sys.DTKUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKUserServiceImpl extends ServiceImpl<DTKUserMapper, DTKUser> implements DTKUserService {
}
