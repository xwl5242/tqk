package com.quanchong.dataoke.service.good.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanDiscountGood;
import com.quanchong.common.ffquan.FFQuanDiscountResp;
import com.quanchong.common.util.BeanUtil;
import com.quanchong.dataoke.mapper.good.DTKFFQDiscountGoodMapper;
import com.quanchong.dataoke.service.good.DTKFFQDiscountGoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DTKFFQDiscountGoodServiceImpl extends ServiceImpl<DTKFFQDiscountGoodMapper, FFQuanDiscountGood>
        implements DTKFFQDiscountGoodService {

    @Override
    public List<FFQuanDiscountGood> gather() throws Exception {
        List<FFQuanDiscountGood> result = new ArrayList<>();
        JSONArray jsonArray = FFQuanApi.discountCategoryList();
        if(null!=jsonArray && !jsonArray.isEmpty()){
           List<String> cIds = jsonArray.toJavaList(String.class);
           for(String cid: cIds){
               JSONObject jsonObject = FFQuanApi.discountGoods(cid);
               if(null!=jsonObject){
                   FFQuanDiscountResp resp = jsonObject.toJavaObject(FFQuanDiscountResp.class);
                   if(null!=resp && !resp.getList().isEmpty()){
                       result.addAll(resp.getList());
                   }
               }
           }
        }
        return result;
    }
}
