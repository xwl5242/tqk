package com.quanchong.dataoke.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKCollect;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.mapper.sys.DTKCollectMapper;
import com.quanchong.dataoke.service.sys.DTKCollectService;
import com.quanchong.dataoke.service.good.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DTKCollectServiceImpl extends ServiceImpl<DTKCollectMapper, DTKCollect> implements DTKCollectService {

    @Autowired
    private DTKCollectMapper dtkCollectMapper;

    @Autowired
    private DTKGoodService dtkGoodService;

    @Override
    public List<DTKGood> selectCollectGoods(String openId) {
        QueryWrapper<DTKCollect> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        wrapper.eq("is_del", "0");
        List<DTKCollect> collects = dtkCollectMapper.selectList(wrapper);
        if(null!=collects && !collects.isEmpty()){
            List<String> itemIds = collects.stream().map(DTKCollect::getGoodId).collect(Collectors.toList());
            Map<String,String> collectMap = collects.stream().collect(Collectors.toMap(DTKCollect::getGoodId, DTKCollect::getId));
            List<DTKGood> goods = new ArrayList<>(dtkGoodService.listByIds(itemIds));
            return goods.stream().map(g->{
                g.setCollectId(collectMap.get(g.getId()));
                return g;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
