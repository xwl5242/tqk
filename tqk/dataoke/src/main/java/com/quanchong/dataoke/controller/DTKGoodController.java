package com.quanchong.dataoke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.dataoke.DTKSortEnum;
import com.quanchong.dataoke.dataoke.entity.DTKActivity;
import com.quanchong.dataoke.dataoke.entity.DTKCategory;
import com.quanchong.dataoke.dataoke.entity.DTKTopic;
import com.quanchong.dataoke.entity.DTKGood;
import com.quanchong.dataoke.entity.DTKGoodCoupon;
import com.quanchong.dataoke.service.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 大淘客controller
 */
@RestController
@RequestMapping("/dtk")
public class DTKGoodController {

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
        List<DTKTopic> topicList = dtkService.getTopic();
        return topicList.stream().filter(dtkTopic -> dtkTopic.getBanner().size()!=0).collect(Collectors.toList());
    }

    /**
     *
     */
    @PostMapping("/create_test_data")
    public void createTestData(){
        dtkGoodService.createTestData();
    }

    /**
     * 根据类目id查询商品列表
     * @param cid
     * @return
     * @throws Exception
     */
    @GetMapping("/goods/{cid}")
    public List<DTKGood> queryByCId(@PathVariable String cid, String sort) throws Exception{
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        sort = StringUtils.isEmpty(sort)? DTKSortEnum.USE_COUPON_DESC.getCode() :sort;
        if(sort.equals(DTKSortEnum.USE_COUPON_DESC.getCode())){
            wrapper.orderByDesc("coupon_receive_num");
        }else if(sort.equals(DTKSortEnum.CTIME_DESC.getCode())){
            wrapper.orderByDesc("create_time");
        }else if(sort.equals(DTKSortEnum.RESUME_DESC.getCode())){
            wrapper.orderByDesc("month_sales");
        }else{
            wrapper.orderByDesc("actual_price");
        }
        wrapper.eq("is_expire", "0");
        return dtkGoodService.list(wrapper);
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

    /**
     * 获取商品详情
     * @param id 商品id
     * @return
     */
    @GetMapping("/goods/detail/{id}")
    public DTKGood queryById(@PathVariable String id) throws Exception{
        return dtkService.getGoodDetail(id);
    }

}
