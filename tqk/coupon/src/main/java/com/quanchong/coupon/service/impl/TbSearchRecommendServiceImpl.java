package com.quanchong.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.TbSearchRecommend;
import com.quanchong.coupon.mapper.TbSearchRecommendMapper;
import com.quanchong.coupon.service.TbSearchRecommendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TbSearchRecommendServiceImpl
        extends ServiceImpl<TbSearchRecommendMapper, TbSearchRecommend> implements TbSearchRecommendService {
}
