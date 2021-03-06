package com.quanchong.dataoke.service.good.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanDiscountGood;
import com.quanchong.common.ffquan.FFQuanDiscountResp;
import com.quanchong.common.util.BeanUtil;
import com.quanchong.dataoke.dataoke.util.GoodUtils;
import com.quanchong.dataoke.mapper.good.DTKFFQDiscountGoodMapper;
import com.quanchong.dataoke.service.good.DTKFFQDiscountGoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DTKFFQDiscountGoodServiceImpl extends ServiceImpl<DTKFFQDiscountGoodMapper, FFQuanDiscountGood>
        implements DTKFFQDiscountGoodService {

    @Override
    public List<FFQuanDiscountGood> gather() throws Exception {
        List<FFQuanDiscountGood> result = new ArrayList<>();
        JSONArray jsonArray = FFQuanApi.discountCategoryList();
        if(null!=jsonArray && !jsonArray.isEmpty()){
           for(int i=0;i<jsonArray.size();i++){
               String cid = jsonArray.getJSONObject(i).getString("id");
               if(!StringUtils.isEmpty(cid)){
                   JSONObject jsonObject = FFQuanApi.discountGoods(cid);
                   if(null!=jsonObject){
                       FFQuanDiscountResp resp = jsonObject.toJavaObject(FFQuanDiscountResp.class);
                       if(null!=resp && !resp.getList().isEmpty()){
                           List<FFQuanDiscountGood> goods = resp.getList();
                           goods = goods.parallelStream().map(ffQuanDiscountGood -> {
                               ffQuanDiscountGood.setCId(cid);
                               return ffQuanDiscountGood;
                           }).collect(Collectors.toList());
                           result.addAll(goods);
                       }
                   }
               }
           }
        }
        result = result.parallelStream()
                .filter(GoodUtils.distinctByKey(FFQuanDiscountGood::getId)).collect(Collectors.toList());
        return result;
    }
}
