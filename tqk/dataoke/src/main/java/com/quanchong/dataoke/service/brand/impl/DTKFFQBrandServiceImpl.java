package com.quanchong.dataoke.service.brand.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanBrand;
import com.quanchong.common.ffquan.FFQuanBrandGood;
import com.quanchong.dataoke.mapper.brand.DTKFFQBrandMapper;
import com.quanchong.dataoke.service.brand.DTKFFQBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DTKFFQBrandServiceImpl extends ServiceImpl<DTKFFQBrandMapper, FFQuanBrand>
        implements DTKFFQBrandService {

    /**
     * 采集ffquan平台推荐品牌信息
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> gather() throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<FFQuanBrand> brandList = new ArrayList<>();
        List<FFQuanBrandGood> goodList = new ArrayList<>();
        gatherCommon(FFQuanApi.brandRecsList(),"recs", brandList, goodList);
        gatherCommon(FFQuanApi.brandHotsList(), "hots", brandList, goodList);
        result.put("brands", brandList);
        result.put("goods", goodList);
        return result;
    }

    /**
     * ffquan 品牌以及品牌商品采集共同部分
     * @param jsonArray
     * @return
     * @throws Exception
     */
    private void gatherCommon(JSONArray jsonArray, String brandType,
                                             List<FFQuanBrand> brandList, List<FFQuanBrandGood> goodList) throws Exception {
        if(null!=jsonArray && !jsonArray.isEmpty()){
            List<FFQuanBrand> brands = jsonArray.toJavaList(FFQuanBrand.class);
            if(null!=brands && !brands.isEmpty()){
                brands.stream().forEach(ffQuanBrand -> {
                    List<FFQuanBrandGood> brandGoods = ffQuanBrand.getHotPush();
                    if(null!=brandGoods && !brandGoods.isEmpty()){
                        goodList.addAll(brandGoods.parallelStream().map(ffQuanBrandGood -> {
                            ffQuanBrandGood.setBrandId(ffQuanBrand.getBrandId());
                            return ffQuanBrandGood;
                        }).collect(Collectors.toList()));
                    }
                    ffQuanBrand.setBrandType(brandType);
                    brandList.add(ffQuanBrand);
                });
            }
        }
    }
}
