package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.dataoke.entity.DTKCollect;
import com.quanchong.dataoke.entity.DTKGood;

import java.util.List;

public interface DTKCollectService extends IService<DTKCollect> {
    List<DTKGood> selectCollectGoods(String openId);
}
