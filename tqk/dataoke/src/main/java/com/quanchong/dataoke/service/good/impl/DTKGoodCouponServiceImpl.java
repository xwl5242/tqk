package com.quanchong.dataoke.service.good.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKGoodCoupon;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.mapper.good.DTKGoodCouponMapper;
import com.quanchong.dataoke.service.good.DTKGoodCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKGoodCouponServiceImpl extends ServiceImpl<DTKGoodCouponMapper, DTKGoodCoupon> implements DTKGoodCouponService {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKGoodCouponMapper dtkGoodCouponMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DTKGoodCoupon queryGoodCoupon(String itemId) throws Exception {
        QueryWrapper<DTKGoodCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", itemId);
        wrapper.eq("is_expire", "0");
        DTKGoodCoupon gc = dtkGoodCouponMapper.selectOne(wrapper);
        if(null == gc){
            DTKGoodCoupon newGC = dtkService.privilegeLinkList(itemId);
            if(null != newGC){
                int insertRet = dtkGoodCouponMapper.insert(newGC);
                gc = insertRet==1?newGC:null;
            }
        }
        return gc;
    }
}
