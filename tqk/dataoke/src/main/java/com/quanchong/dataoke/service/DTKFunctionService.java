package com.quanchong.dataoke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.service.DTKFunction;

public interface DTKFunctionService extends IService<DTKFunction> {
    public boolean openFunction(String functionName);
    public boolean closeFunction(String functionName);
    public DTKFunction getFunction(String functionName);
    public String getFunctionValue(String functionName);
    public boolean setFunctionValue(String functionName, String functionValue);
}
