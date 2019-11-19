package com.quanchong.server.feign;

import com.quanchong.common.entity.TbCouponMaterial;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "tqk-web", url = "http://localhost:8085")
@RequestMapping("/tb/coupon/material")
public interface TbCouponMaterialFeignClient {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<TbCouponMaterial> queryList(@RequestParam Map<String, Object> params);

}
