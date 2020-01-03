package com.quanchong.dataoke.service.good;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.dtkResp.GoodStaleResp;
import com.quanchong.common.entity.service.DTKGood;

import java.util.List;

public interface DTKGoodService extends IService<DTKGood> {
    List<DTKGood> gatherGoods() throws Exception;
    List<DTKGood> gatherGoodsByNine() throws Exception;
    List<DTKGood> gatherGoodsByRanking() throws Exception;
    List<DTKGood> gatherGoodsByPull() throws Exception;
    List<DTKGood> gatherGoodsByNewest() throws Exception;
    List<GoodStaleResp.GoodStale> gatherGoodsByStale() throws Exception;
    String getOldestGoodStartTime();
}
