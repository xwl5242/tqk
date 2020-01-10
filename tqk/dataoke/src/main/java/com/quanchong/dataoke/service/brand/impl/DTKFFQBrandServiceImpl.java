package com.quanchong.dataoke.service.brand.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanBrand;
import com.quanchong.common.ffquan.FFQuanBrandGood;
import com.quanchong.dataoke.dataoke.util.GoodUtils;
import com.quanchong.dataoke.mapper.brand.DTKFFQBrandMapper;
import com.quanchong.dataoke.service.brand.DTKFFQBrandGoodService;
import com.quanchong.dataoke.service.brand.DTKFFQBrandService;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class DTKFFQBrandServiceImpl extends ServiceImpl<DTKFFQBrandMapper, FFQuanBrand>
        implements DTKFFQBrandService {

    @Autowired
    private DTKFFQBrandGoodService dtkffqBrandGoodService;

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
        // 先采集"精选"页面品牌商品数据
        gatherCommon(FFQuanApi.brandRecsList(),"recs", brandList, goodList);
        gatherCommon(FFQuanApi.brandHotsList(), "hots", brandList, goodList);
        // 再根据categoryId采集各类目品牌商品数据
        JSONArray jsonArray = FFQuanApi.brandCategoryList();
        if(null!=jsonArray && !jsonArray.isEmpty()){
            for(int i=0;i<jsonArray.size();i++){
                String typeId = jsonArray.getJSONObject(i).getString("typeId");
                if(!StringUtils.isEmpty(typeId)){
                    JSONArray brandJsonArray = FFQuanApi.brandListByCategoryId(typeId);
                    gatherCommon(brandJsonArray, typeId, brandList, goodList);
                }
            }
        }
        brandList = brandList.parallelStream()
                .filter(GoodUtils.distinctByKey(FFQuanBrand::getBrandId)).collect(Collectors.toList());
        goodList = goodList.parallelStream()
                .filter(GoodUtils.distinctByKey(FFQuanBrandGood::getId)).collect(Collectors.toList());
        result.put("brands", brandList);
        result.put("goods", goodList);
        return result;
    }

    /**
     * 品牌以及品牌商品list信息
     * @return
     */
    @Override
    public List<FFQuanBrand> listByTypeOrCategory(String typeOrCategory, Long pageNo, Long pageSize) {
        pageNo = null == pageNo? 1L: pageNo;
        pageSize = null == pageSize? 20L: pageSize;
        QueryWrapper<FFQuanBrand> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", typeOrCategory);
        IPage<FFQuanBrand> pageList = page(new Page<>(pageNo, pageSize), wrapper);
        List<FFQuanBrand> list = pageList.getRecords();
        return list.parallelStream().map(ffQuanBrand -> {
            String brandId = ffQuanBrand.getBrandId();
            QueryWrapper<FFQuanBrandGood> wrapper1 =  new QueryWrapper<>();
            wrapper1.eq("brand_id", brandId);
            List<FFQuanBrandGood> ffqBrandGoods = dtkffqBrandGoodService.list(wrapper1);
            ffQuanBrand.setHotPush(ffqBrandGoods.subList(0,3));
            return ffQuanBrand;
        }).collect(Collectors.toList());
    }

    /**
     * ffquan 品牌以及品牌商品采集共同部分
     * @param jsonArray
     * @return
     * @throws Exception
     */
    private void gatherCommon(JSONArray jsonArray, String brandTypeOrCatetoryId,
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
                    ffQuanBrand.setBrandType(brandTypeOrCatetoryId);
                    ffQuanBrand.setCategoryId(brandTypeOrCatetoryId);
                    brandList.add(ffQuanBrand);
                });
            }
        }
    }

}
