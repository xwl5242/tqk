package com.quanchong.dataoke.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKOfficialActivity;
import com.quanchong.common.util.ColorThief;
import com.quanchong.common.util.DateUtils;
import com.quanchong.dataoke.service.sys.DTKOfficialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        QueryWrapper<DTKOfficialActivity> wrapper = getListQueryWrapper("wifi");
        return dtkOfficialActivityService.list(wrapper).stream().map(dtkOfficialActivity -> {
            String url = dtkOfficialActivity.getActivityImgUrl();
            String color = ColorThief.getImagePixelFromUrl(url);
            dtkOfficialActivity.setMainColor(color);
            return dtkOfficialActivity;
        }).collect(Collectors.toList());
    }

    /**
     * 获取pc端官方活动列表
     * @return
     */
    @GetMapping("/list/pc")
    public List<DTKOfficialActivity> getPcList(){
        return dtkOfficialActivityService.list(getListQueryWrapper("pc"));
    }

    private QueryWrapper<DTKOfficialActivity> getListQueryWrapper(String platform){
        String nowDate = DateUtils.nowDate();
        QueryWrapper<DTKOfficialActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_platform", platform);
        wrapper.lt("activity_start_time", nowDate);
        wrapper.gt("activity_end_time", nowDate);
        return wrapper;
    }
}
