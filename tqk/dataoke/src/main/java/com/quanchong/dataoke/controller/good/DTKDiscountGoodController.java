package com.quanchong.dataoke.controller.good;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanDiscountGood;
import com.quanchong.common.ffquan.FFQuanDiscountResp;
import com.quanchong.dataoke.service.good.DTKFFQDiscountGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dtk/goods")
public class DTKDiscountGoodController {

    @Autowired
    private DTKFFQDiscountGoodService dtkffqDiscountGoodService;

    /**
     * 查询折上折类目id List
     */
    @GetMapping("/ffquan/discount_category")
    public JSONArray queryFFQuanDiscountCategory() throws Exception{
        return FFQuanApi.discountCategoryList();
    }

    /**
     * ffquan 查询折上折商品
     * @param cId 折上折类目id
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/discount")
    public List<FFQuanDiscountGood> queryFFQuanDiscountGoods(@RequestParam String cId,
                                                             @RequestParam Long pageNo, @RequestParam Long pageSize) throws Exception{
        pageNo = null == pageNo? 1L: pageNo;
        pageSize = null == pageSize? 20L: pageSize;
        QueryWrapper<FFQuanDiscountGood> wrapper = new QueryWrapper<>();
        wrapper.eq("c_id", cId);
        IPage<FFQuanDiscountGood> pageList = dtkffqDiscountGoodService.page(new Page<>(pageNo, pageSize), wrapper);
        return pageList.getRecords();
    }
}
