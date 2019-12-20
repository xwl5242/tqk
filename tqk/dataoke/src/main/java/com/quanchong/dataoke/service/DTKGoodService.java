package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.dataoke.entity.DTKGood;

public interface DTKGoodService extends IService<DTKGood> {
    void createTestData();
}
