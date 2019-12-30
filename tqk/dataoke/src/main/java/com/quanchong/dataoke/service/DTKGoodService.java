package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.service.DTKGood;

public interface DTKGoodService extends IService<DTKGood> {
    void gather() throws RuntimeException;
}
