package com.quanchong.dataoke.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.dataoke.entity.DTKActivity;
import com.quanchong.dataoke.dataoke.entity.DTKCategory;
import com.quanchong.dataoke.dataoke.entity.DTKTopic;
import com.quanchong.dataoke.entity.DTKGood;
import com.quanchong.dataoke.service.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 大淘客controller
 */
@RestController
@RequestMapping("/dtk")
public class DTKController {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKGoodService dtkGoodService;

    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    @GetMapping("/super_category")
    public List<DTKCategory> getSuperCategory() throws Exception{
        return dtkService.getSuperCategory();
    }

    /**
     * 获取官方活动
     * @return
     * @throws Exception
     */
    @GetMapping("/activity")
    public List<DTKActivity> getActivity() throws Exception{
        return dtkService.getActivity();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/topic")
    public List<DTKTopic> getTopic() throws Exception{
        return dtkService.getTopic();
    }

    /**
     * 获取商品list
     * @param pageNo 页面
     * @param pageSize 页大小
     * @return
     */
    @GetMapping("/goods/page")
    public List<DTKGood> findPageList(Long pageNo, Long pageSize){
        IPage<DTKGood> page = new Page<>();
        page.setCurrent(ObjectUtils.isEmpty(pageNo)?1L:pageNo);
        page.setSize(ObjectUtils.isEmpty(pageSize)?50L:pageSize);
        IPage<DTKGood> goodPage = dtkGoodService.page(page);
        return goodPage.getRecords();
    }
}
