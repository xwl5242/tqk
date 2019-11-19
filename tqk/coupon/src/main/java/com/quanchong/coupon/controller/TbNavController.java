package com.quanchong.coupon.controller;

import com.quanchong.common.entity.TbNav;
import com.quanchong.coupon.service.TbNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tb/nav")
public class TbNavController {

    @Autowired
    private TbNavService tbNavService;

    @GetMapping("/list")
    public List<TbNav> list(){
        return tbNavService.list().stream()
                .filter(tbNav -> tbNav.getDelFlag().equals("0"))
                .collect(Collectors.toList());
    }
}
