package com.quanchong.dataoke.controller;

import com.quanchong.dataoke.entity.DTKGoodCoupon;
import com.quanchong.dataoke.service.DTKGoodCouponService;
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
