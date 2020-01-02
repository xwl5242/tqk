package com.quanchong.dataoke.job;

import com.quanchong.common.entity.dtkResp.GoodStaleResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.service.DTKGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableScheduling
public class DTKGoodsJob{

    @Autowired
    private DTKGoodService dtkGoodService;

    /**
     * 每30分钟拉取一次商品数据
     * @throws Exception
     */
    @Scheduled(initialDelay = 5*1000, fixedDelay=30*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByPull() throws Exception{
        List<DTKGood> list = dtkGoodService.gatherGoodsByPull();
        dtkGoodService.saveBatch(list);
    }

    /**
     * 每10分钟拉取一次失效商品数据
     * @throws Exception
     */
    @Scheduled(initialDelay = 3*1000, fixedDelay=10*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByStale() throws Exception{
        List<GoodStaleResp.GoodStale> list = dtkGoodService.gatherGoodsByStale();
        List<DTKGood> goodList = list.stream().map(goodStale -> {
            DTKGood good = new DTKGood();
            good.setId(goodStale.getId());
            good.setIsExpire("1");
            return good;
        }).collect(Collectors.toList());
        dtkGoodService.updateBatchById(goodList);
    }

    /**
     * 每1个小时拉取一次商品变更数据
     * @throws Exception
     */
    @Scheduled(initialDelay = 7*1000, fixedDelay=60*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByNewest() throws Exception{
        List<DTKGood> list = dtkGoodService.gatherGoodsByNewest();
        dtkGoodService.updateBatchById(list);
    }

}