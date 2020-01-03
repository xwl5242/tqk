package com.quanchong.dataoke.service.good;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.ffquan.FFQuanDiscountGood;

import java.util.List;

public interface DTKFFQDiscountGoodService extends IService<FFQuanDiscountGood> {

    List<FFQuanDiscountGood> gather() throws Exception;
}
