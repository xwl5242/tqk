package com.quanchong.dataoke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.dtkResp.GoodStaleResp;
import com.quanchong.common.entity.dtkResp.SuperCategoryResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.util.DateUtils;
import com.quanchong.common.util.StringUtil;
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
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
public class DTKGoodServiceImpl extends ServiceImpl<DTKGoodMapper, DTKGood> implements DTKGoodService {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private DTKFunctionService dtkFunctionService;

    private List<String> rankTypeList;

    private List<String> cidList;

    private List<String> nineCidList;

    private String lastPullGatherTime;

    private String lastStaleGatherTime;

    /**
     * 查询所有类目id
     */
    @PostConstruct
    public void init() {
        log.info("DTKService init...");
        try{
            rankTypeList = Stream.of("1", "2").collect(Collectors.toList());
            nineCidList = Stream.of("-1","1","2","3","4","5","6","7","8","9","10").collect(Collectors.toList());
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
     * 拉取9.9包邮商品
     * @return
     * @throws Exception
     */
    @Override
    public List<DTKGood> gatherGoodsByNine() throws Exception{
        List<DTKGood> goodList = new ArrayList<>();
        for(String nineCid: nineCidList){
            gatherGoodsByNineLoop(goodList, nineCid, "1");
        }
        goodList = goodList.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
        log.info("定时采集9.9商品数据记录:{}条", goodList.size());
        return goodList;
    }

    /**
     * 拉取榜单商品
     */
    @Override
    public List<DTKGood> gatherGoodsByRanking() throws Exception{
        List<DTKGood> goodList = new ArrayList<>();
        for(String rankType: rankTypeList){
            for(String cid: cidList){
                List<DTKGood> tmpList = dtkService.goodsByRanking(rankType, cid);
                // 设置商品榜单相关字段值
                tmpList = tmpList.parallelStream().map(dtkGood -> {
                    dtkGood.setRank("1");
                    dtkGood.setRankType(rankType);
                    return dtkGood;
                }).collect(Collectors.toList());
                goodList.addAll(tmpList);
            }
        }
        goodList = goodList.parallelStream().filter(distinctByKey(DTKGood::getId)).collect(Collectors.toList());
        log.info("定时采集榜单商品数据记录:{}条", goodList.size());
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
        String createTime = getOldestGoodStartTime();
        for(String cid: cidList){
            gatherGoodsByNewestLoop(goodList, cid, "1", createTime);
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
     * 查询商品上架时间的最小值
     * @return
     */
    @Override
    public String getOldestGoodStartTime() {
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("is_expire", "0");
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        List<DTKGood> list = list(wrapper);
        if(null!=list && !list.isEmpty()){
            return list.get(0).getCreateTime();
        }
        return null;
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
     * 采集9.9商品递归调用
     * @param goods
     * @param cid
     * @param pageId
     * @throws Exception
     */
    private void gatherGoodsByNineLoop(List<DTKGood> goods, String cid, String pageId) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodResp goodResp = dtkService.goodsByNine(pageId, "", cid);
            if(null!=goodResp && !goodResp.getList().isEmpty()){
                List<DTKGood> tmpList = goodResp.getList();
                // 设置商品9.9相关字段值
                tmpList = tmpList.parallelStream().map(dtkGood -> {
                    dtkGood.setNine("1");
                    dtkGood.setNineCid(cid);
                    return dtkGood;
                }).collect(Collectors.toList());
                goods.addAll(tmpList);
                gatherGoodsByNineLoop(goods, cid, goodResp.getPageId());
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
    private void gatherGoodsByNewestLoop(List<DTKGood> goods, String cid, String pageId, String createTime) throws Exception{
        if(!StringUtils.isEmpty(pageId)){
            GoodResp goodResp = dtkService.goodsByNewest(pageId, "", cid, "", "0", "", createTime, "");
            if(null!=goodResp && !goodResp.getList().isEmpty()){
                goods.addAll(goodResp.getList());
                gatherGoodsByNewestLoop(goods, cid, goodResp.getPageId(), createTime);
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
