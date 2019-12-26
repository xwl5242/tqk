package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.dataoke.entity.DTKOfficialActivity;
import com.quanchong.dataoke.mapper.DTKOfficialActivityMapper;
import com.quanchong.dataoke.service.DTKOfficialActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKOfficialActivityServiceImpl extends ServiceImpl<DTKOfficialActivityMapper, DTKOfficialActivity>
        implements DTKOfficialActivityService {
}
