package com.quanchong.dataoke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKOfficialActivity;
import com.quanchong.common.util.DateUtils;
import com.quanchong.dataoke.service.DTKOfficialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dtk/activity")
public class DTKOfficialActivityController {

    @Autowired
    private DTKOfficialActivityService dtkOfficialActivityService;

    /**
     * 获取无线端官方活动列表
     * @return
     */
    @GetMapping("/list/wifi")
    public List<DTKOfficialActivity> getWifiList(){
        return dtkOfficialActivityService.list(getListQueryWrapper("wifi"));
    }

    /**
     * 获取pc端官方活动列表
     * @return
     */
    @GetMapping("/list/pc")
    public List<DTKOfficialActivity> getPcList(){
        return dtkOfficialActivityService.list(getListQueryWrapper("pc"));
    }

    private QueryWrapper getListQueryWrapper(String platform){
        String nowDate = DateUtils.nowDate();
        QueryWrapper<DTKOfficialActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_platform", platform);
        wrapper.lt("activity_start_time", nowDate);
        wrapper.gt("activity_end_time", nowDate);
        return wrapper;
    }
}
