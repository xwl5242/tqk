package com.quanchong.dataoke.controller.good;

import com.quanchong.common.entity.service.DTKGoodCoupon;
import com.quanchong.dataoke.service.good.DTKGoodCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dtk")
public class DTKGoodCouponController {

    @Autowired
    private DTKGoodCouponService dtkGoodCouponService;

    @GetMapping("/goods/coupon")
    public DTKGoodCoupon queryGoodCoupon(@RequestParam String itemId) throws Exception{
        return dtkGoodCouponService.queryGoodCoupon(itemId);
    }

}
