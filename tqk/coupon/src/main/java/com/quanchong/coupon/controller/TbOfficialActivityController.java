package com.quanchong.coupon.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.TbOfficialActivity;
import com.quanchong.coupon.service.TbOfficialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tb/official/activity")
public class TbOfficialActivityController {

    @Autowired
    private TbOfficialActivityService tbOfficialActivityService;

    @GetMapping("/{platform}")
    public List<TbOfficialActivity> queryByPlatform(@PathVariable("platform") String platform){
        QueryWrapper<TbOfficialActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("platform", platform);
        return tbOfficialActivityService.list(wrapper);
    }
}
