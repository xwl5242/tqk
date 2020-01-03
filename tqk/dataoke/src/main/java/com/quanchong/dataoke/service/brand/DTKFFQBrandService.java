package com.quanchong.dataoke.service.brand;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.ffquan.FFQuanBrand;

import java.util.Map;

public interface DTKFFQBrandService extends IService<FFQuanBrand> {
    Map<String, Object> gather() throws Exception;
}
