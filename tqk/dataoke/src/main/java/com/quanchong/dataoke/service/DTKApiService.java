package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.dataoke.entity.DTKApi;

public interface DTKApiService extends IService<DTKApi> {
    DTKApi getBYApiKey(String apiKey);
}
