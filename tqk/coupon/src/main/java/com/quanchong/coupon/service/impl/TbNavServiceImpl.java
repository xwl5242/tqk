package com.quanchong.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.TbNav;
import com.quanchong.coupon.mapper.TbNavMapper;
import com.quanchong.coupon.service.TbNavService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TbNavServiceImpl extends ServiceImpl<TbNavMapper, TbNav> implements TbNavService {
}
