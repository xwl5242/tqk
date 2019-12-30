package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.dtkResp.SuperCategoryResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.mapper.DTKGoodMapper;
import com.quanchong.dataoke.service.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class DTKGoodServiceImpl extends ServiceImpl<DTKGoodMapper, DTKGood> implements DTKGoodService {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKGoodMapper dtkGoodMapper;

    @Override
    public void gather() throws RuntimeException{
        try{
            // 所有类目id
            List<SuperCategoryResp> superCategoryResps = dtkService.superCategoryList();
            List<String> cidList = superCategoryResps.stream()
                    .map(SuperCategoryResp::getCid).collect(Collectors.toList());
            // 所有9.9商品id
            List<String> nineCidList = Stream.of("-1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                    .collect(Collectors.toList());
            // 所有榜单类型id
            List<String> rankTypeList = Stream.of("1", "2").collect(Collectors.toList());
            // 要保存的所有商品
            List<DTKGood> goods = new ArrayList<>();
            // 所有类目id商品List
            for(String cid: cidList){
                GoodResp resp = dtkService.goods("1", "100", "", cid, "");
                goods.addAll(resp.getList());
            }
            System.out.println("添加完所有类目id的商品后:" + goods.size());
            // 所有9.9商品List
            for(String nineCid: nineCidList){
                GoodResp resp = dtkService.goodsByNine("1", "100", nineCid);
                // 设置商品的nine 和 nineCid
                List<DTKGood> goodList = resp.getList().stream().map(good->{
                    good.setNine("1");
                    good.setNineCid(nineCid);
                    return good;
                }).collect(Collectors.toList());
                goods.addAll(goodList);
            }
            System.out.println("添加完所有9.9元商品后:" + goods.size());
            // 所有榜单商品
            for(String rankType: rankTypeList){
                List<DTKGood> goodList = dtkService.goodsByRanking(rankType);
                // 设置商品的rank 和 rankType
                goodList = goodList.stream().map(good-> {
                    good.setRank("1");
                    good.setRankType(rankType);
                    return good;
                }).collect(Collectors.toList());
                goods.addAll(goodList);
            }
            System.out.println("添加完所有商品后:" + goods.size());
            goods = goods.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
            System.out.println("去重后:" + goods.size());
            saveBatch(goods);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
