package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.service.DTKCollect;
import com.quanchong.common.entity.service.DTKGood;

import java.util.List;

public interface DTKCollectService extends IService<DTKCollect> {
    List<DTKGood> selectCollectGoods(String openId);
}
