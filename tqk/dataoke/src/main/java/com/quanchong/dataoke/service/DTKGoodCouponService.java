package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.service.DTKGoodCoupon;

public interface DTKGoodCouponService extends IService<DTKGoodCoupon> {
    DTKGoodCoupon queryGoodCoupon(String itemId) throws Exception;
}
