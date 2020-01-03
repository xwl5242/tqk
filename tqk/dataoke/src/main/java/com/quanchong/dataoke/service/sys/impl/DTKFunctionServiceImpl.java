package com.quanchong.dataoke.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.service.DTKFunction;
import com.quanchong.dataoke.mapper.sys.DTKFunctionMapper;
import com.quanchong.dataoke.service.sys.DTKFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DTKFunctionServiceImpl extends ServiceImpl<DTKFunctionMapper, DTKFunction> implements DTKFunctionService {

    /**
     * 开启功能
     *
     * @return
     */
    @Override
    public boolean openFunction(String functionName) {
        DTKFunction function = getFunction(functionName);
        function.setFunctionSwitch("1");
        return updateById(function);
    }

    /**
     * 关闭功能
     */
    @Override
    public boolean closeFunction(String functionName) {
        DTKFunction function = getFunction(functionName);
        function.setFunctionSwitch("0");
        return updateById(function);
    }

    /**
     * 获取功能详情信息
     *
     * @return
     */
    @Override
    public DTKFunction getFunction(String functionName) {
        QueryWrapper<DTKFunction> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", "0");
        wrapper.eq("function_name", functionName);
        return getOne(wrapper);
    }

    /**
     * 获取功能值
     * @param functionName
     * @return
     */
    @Override
    public String getFunctionValue(String functionName) {
        return getFunction(functionName).getFunctionValue();
    }

    /**
     * 设置功能值
     * @param functionName
     * @param functionValue
     * @return
     */
    @Override
    public boolean setFunctionValue(String functionName, String functionValue) {
        DTKFunction function = getFunction(functionName);
        function.setFunctionValue(functionValue);
        return updateById(function);
    }
}
