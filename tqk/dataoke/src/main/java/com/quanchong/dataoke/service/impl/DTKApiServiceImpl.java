package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.dataoke.entity.DTKApi;
import com.quanchong.dataoke.mapper.DTKApiMapper;
import com.quanchong.dataoke.service.DTKApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKApiServiceImpl extends ServiceImpl<DTKApiMapper, DTKApi> implements DTKApiService {

    @Autowired
    private DTKApiMapper dtkApiMapper;

    @Override
    public DTKApi getBYApiKey(String apiKey) {
        QueryWrapper<DTKApi> wrapper = new QueryWrapper<>();
        wrapper.eq("api_key", apiKey);
        return dtkApiMapper.selectOne(wrapper);
    }
}
