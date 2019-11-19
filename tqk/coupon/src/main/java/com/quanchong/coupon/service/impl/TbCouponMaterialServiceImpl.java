package com.quanchong.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.TbCouponMaterial;
import com.quanchong.coupon.mapper.TbCouponMaterialMapper;
import com.quanchong.coupon.service.TbCouponMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TbCouponMaterialServiceImpl extends
        ServiceImpl<TbCouponMaterialMapper, TbCouponMaterial> implements TbCouponMaterialService {
}
