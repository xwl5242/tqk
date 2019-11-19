package com.quanchong.coupon.controller;

import com.quanchong.common.entity.TbSearchRecommend;
import com.quanchong.coupon.service.TbSearchRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tb/search/recommend")
public class TbSearchRecommendController {

    @Autowired
    private TbSearchRecommendService tbSearchRecommendService;

    @GetMapping("/list")
    public List<TbSearchRecommend> list(){
        return tbSearchRecommendService.list();
    }
}
