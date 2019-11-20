package com.quanchong.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.TbGood;
import com.quanchong.coupon.mapper.TbGoodMapper;
import com.quanchong.coupon.service.TbGoodService;
import org.springframework.stereotype.Service;

@Service
public class TbGoodServiceImpl extends ServiceImpl<TbGoodMapper, TbGood> implements TbGoodService {
}
