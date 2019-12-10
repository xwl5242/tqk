package com.quanchong.dataoke.controller;

import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.dataoke.entity.DTKCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大淘客controller
 */
@RestController
@RequestMapping("/dtk")
public class DTKController {

    @Autowired
    private DTKService dtkService;

    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    @GetMapping("/super_category")
    public DTKCategory getSuperCategory() throws Exception{
        return dtkService.getSuperCategory();
    }
}
