package com.quanchong.dataoke.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKIconNav;
import com.quanchong.dataoke.service.sys.DTKIconNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dtk/icon_nav")
public class DTKIconNavController {

    @Autowired
    private DTKIconNavService dtkIconNavService;

    @GetMapping("/list")
    public List<DTKIconNav> list(){
        QueryWrapper<DTKIconNav> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", "0");
        return dtkIconNavService.list(wrapper);
    }
}
