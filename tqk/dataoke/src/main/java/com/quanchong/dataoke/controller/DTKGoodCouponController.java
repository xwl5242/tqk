package com.quanchong.dataoke.controller;

import com.quanchong.dataoke.entity.DTKGoodCoupon;
import com.quanchong.dataoke.service.DTKGoodCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dtk")
public class DTKGoodCouponController {

    @Autowired
    private DTKGoodCouponService dtkGoodCouponService;

    @GetMapping("/goods/coupon/{itemId}")
    public DTKGoodCoupon queryGoodCoupon(@PathVariable String itemId) throws Exception{
        return dtkGoodCouponService.queryGoodCoupon(itemId);
    }

}
