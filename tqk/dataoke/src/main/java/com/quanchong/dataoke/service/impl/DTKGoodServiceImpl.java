package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.mapper.DTKGoodMapper;
import com.quanchong.dataoke.service.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DTKGoodServiceImpl extends ServiceImpl<DTKGoodMapper, DTKGood> implements DTKGoodService {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKGoodMapper dtkGoodMapper;

    @Override
    public void createTestData() {
        try{
            GoodResp resp = dtkService.getGoods(null);
            List<DTKGood> list = new DTKGood().transforList(resp.getList(), "desc", "description");
            saveBatch(list);
        }catch(Exception e){
        }
    }
}
