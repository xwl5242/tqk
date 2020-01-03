package com.quanchong.dataoke.controller.sys;

import com.quanchong.common.entity.service.DTKFunction;
import com.quanchong.dataoke.dataoke.DTKConsts;
import com.quanchong.dataoke.service.sys.DTKFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统功能模块开关
 */
@RestController
@RequestMapping("/dtk/function")
public class DTKFunctionController {

    @Autowired
    private DTKFunctionService dtkFunctionService;

    @GetMapping("/get")
    public DTKFunction getFunction(String functionName) {
        return dtkFunctionService.getFunction(functionName);
    }

    /**
     * 获取系统启动时采集商品功能信息
     * @return
     */
    @GetMapping("/get_startup_runner")
    public DTKFunction getStartupRunnerFunction() {
        return dtkFunctionService.getFunction(DTKConsts.DTK_FUNCTION_STARTUP_RUNNER_GATHER_GOODS);
    }

    /**
     * 获取首页品牌商品功能信息
     * @return
     */
    @GetMapping("/get_index_brand")
    public DTKFunction getIndexBrandFunction() {
        return dtkFunctionService.getFunction(DTKConsts.DTK_FUNCTION_INDEX_BRAND);
    }

    /**
     * 开启系统启动时采集商品信息功能
     * @return
     */
    @GetMapping("/open_startup_runner")
    public boolean openStartupRunnerFunction(){
        return dtkFunctionService.openFunction(DTKConsts.DTK_FUNCTION_STARTUP_RUNNER_GATHER_GOODS);
    }

    /**
     * 关闭系统启动时采集商品信息功能
     * @return
     */
    @GetMapping("/close_startup_runner")
    public boolean closeStartupRunnerFunction(){
        return dtkFunctionService.closeFunction(DTKConsts.DTK_FUNCTION_STARTUP_RUNNER_GATHER_GOODS);
    }

    /**
     * 开启系统首页大牌商品功能
     * @return
     */
    @GetMapping("/open_index_brand")
    public boolean openIndexBrandFunction(){
        return dtkFunctionService.openFunction(DTKConsts.DTK_FUNCTION_INDEX_BRAND);
    }

    /**
     * 关闭系统首页大牌商品功能
     * @return
     */
    @GetMapping("/close_index_brand")
    public boolean closeIndexBrandFunction(){
        return dtkFunctionService.closeFunction(DTKConsts.DTK_FUNCTION_INDEX_BRAND);
    }
}
