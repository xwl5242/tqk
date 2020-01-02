package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.dtkResp.GoodStaleResp;
import com.quanchong.common.entity.dtkResp.SuperCategoryResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.util.DateUtils;
import com.quanchong.dataoke.dataoke.DTKConsts;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.mapper.DTKGoodMapper;
import com.quanchong.dataoke.service.DTKFunctionService;
import com.quanchong.dataoke.service.DTKGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DTKGoodServiceImpl extends ServiceImpl<DTKGoodMapper, DTKGood> implements DTKGoodService {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKFunctionService dtkFunctionService;

    private List<String> cidList;

    private String lastPullGatherTime;

    private String lastStaleGatherTime;

    /**
     * 查询所有类目id
     */
    @PostConstruct
    public void init() {
        log.info("DTKService init...");
        try{
            cidList = dtkService.superCategoryList().stream()
                    .map(SuperCategoryResp::getCid).collect(Collectors.toList());
            log.info("DTKService SuperCategoryList:{}", cidList);
            lastPullGatherTime = dtkFunctionService.getFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_PULL_TIME);
            lastStaleGatherTime = dtkFunctionService.getFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_STALE_TIME);
            log.info("DTKService lastPullGatherTime:{}", lastPullGatherTime);
            log.info("DTKService lastStaleGatherTime:{}", lastStaleGatherTime);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 采集各个类目商品500条
     * @throws RuntimeException
     */
    @Override
    public List<DTKGood> gatherGoods() throws Exception{
        List<DTKGood> goodList = new ArrayList<>();
        String now = DateUtils.now();
        lastPullGatherTime = now;
        lastStaleGatherTime = now;
        dtkFunctionService.setFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_PULL_TIME, now);
        dtkFunctionService.setFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_STALE_TIME, now);
        for(String cid: cidList){
            gatherGoodsLoop(goodList, cid, "1");
        }
        //过滤重复数据
        goodList = goodList.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
        log.info("项目启动采集商品数据记录:{}条", goodList.size());
        return goodList;
    }

    /**
     * 拉取商品数据
     * @return
     * @throws Exception
     */
    @Override
    public List<DTKGood> gatherGoodsByPull() throws Exception{
        List<DTKGood> goodList = new ArrayList<>();
        for(String cid: cidList){
            gatherGoodsByPullLoop(goodList, cid, "1");
        }
        String now = DateUtils.now();
        lastPullGatherTime = now;
        dtkFunctionService.setFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_PULL_TIME, now);
        goodList = goodList.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
        log.info("定时拉取商品数据记录:{}条", goodList.size());
        return goodList;
    }

    /**
     * 拉取商品更新数据
     * @return
     * @throws Exception
     */
    @Override
    public List<DTKGood> gatherGoodsByNewest() throws Exception {
        List<DTKGood> goodList = new ArrayList<>();
        for(String cid: cidList){
            gatherGoodsByNewestLoop(goodList, cid, "1");
        }
        goodList = goodList.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
        log.info("定时拉取更新商品数据记录:{}条", goodList.size());
        return goodList;
    }

    /**
     * 拉取失效商品数据
     * @return
     * @throws Exception
     */
    @Override
    public List<GoodStaleResp.GoodStale> gatherGoodsByStale() throws Exception {
        List<GoodStaleResp.GoodStale> list = new ArrayList<>();
        gatherGoodsByStaleLoop(list, "1");
        String now = DateUtils.now();
        lastStaleGatherTime = now;
        dtkFunctionService.setFunctionValue(DTKConsts.DTK_FUNCTION_GATHER_GOODS_STALE_TIME, now);
        list = list.parallelStream().filter(distinctByKey(GoodStaleResp.GoodStale::getId)).collect(Collectors.toList());
        log.info("定时拉取失效商品数据记录:{}条", list.size());
        return list;
    }

    /**
     * 拉取商品数据递归调用
     * @param goods
     * @param cid
     * @param pageId
     * @throws Exception
     */
    private void gatherGoodsLoop(List<DTKGood> goods, String cid, String pageId) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodResp goodResp = dtkService.goods(pageId, "", "", cid, "");
            if(null!=goodResp && !goodResp.getList().isEmpty()){
                goods.addAll(goodResp.getList());
                gatherGoodsLoop(goods, cid, goodResp.getPageId());
            }
        }
    }

    /**
     * 定时拉取商品数据递归调用
     * @param goods
     * @param cid
     * @param pageId
     * @throws Exception
     */
    private void gatherGoodsByPullLoop(List<DTKGood> goods, String cid, String pageId) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodResp goodResp = dtkService.goodsByPull(pageId, "", cid, "", lastPullGatherTime);
            if(null!=goodResp && !goodResp.getList().isEmpty()){
                goods.addAll(goodResp.getList());
                gatherGoodsByPullLoop(goods, cid, goodResp.getPageId());
            }
        }
    }

    /**
     * 拉取商品更新数据递归调用
     * @param goods
     * @param cid
     * @param pageId
     * @throws Exception
     */
    private void gatherGoodsByNewestLoop(List<DTKGood> goods, String cid, String pageId) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodResp goodResp = dtkService.goodsByNewest(pageId, "", cid, "", "0", "");
            if(null!=goodResp && !goodResp.getList().isEmpty()){
                goods.addAll(goodResp.getList());
                gatherGoodsByNewestLoop(goods, cid, goodResp.getPageId());
            }
        }
    }

    /**
     * 拉取失效商品数据递归调用
     * @param goods
     * @param pageId
     * @throws Exception
     */
    private void gatherGoodsByStaleLoop(List<GoodStaleResp.GoodStale> goods, String pageId) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodStaleResp goodStaleResp = dtkService.goodsByStale(pageId, null, lastStaleGatherTime);
            if(null!=goodStaleResp && !goodStaleResp.getList().isEmpty()){
                goods.addAll(goodStaleResp.getList());
                gatherGoodsByStaleLoop(goods, goodStaleResp.getPageId());
            }
        }
    }

    /**
     * 去重操作
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
