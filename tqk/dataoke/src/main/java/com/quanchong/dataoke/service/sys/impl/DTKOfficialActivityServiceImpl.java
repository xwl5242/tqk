package com.quanchong.dataoke.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKOfficialActivity;
import com.quanchong.dataoke.mapper.sys.DTKOfficialActivityMapper;
import com.quanchong.dataoke.service.sys.DTKOfficialActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKOfficialActivityServiceImpl extends ServiceImpl<DTKOfficialActivityMapper, DTKOfficialActivity>
        implements DTKOfficialActivityService {
}
