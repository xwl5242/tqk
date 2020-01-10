package com.quanchong.dataoke.job;

import com.quanchong.common.entity.service.DTKFunction;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.dataoke.DTKConsts;
import com.quanchong.dataoke.service.sys.DTKFunctionService;
import com.quanchong.dataoke.service.good.DTKGoodService;
import jdk.internal.org.objectweb.asm.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static jdk.internal.org.objectweb.asm.ClassReader.EXPAND_FRAMES;
import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

@Slf4j
@Component
public class StartUpRunner extends ClassLoader implements CommandLineRunner {

    @Autowired
    private DTKGoodService dtkGoodService;

    @Autowired
    private DTKFunctionService functionService;

    @Override
    public void run(String... args) throws Exception {
        DTKFunction function = functionService.getFunction(DTKConsts.DTK_FUNCTION_STARTUP_RUNNER_GATHER_GOODS);
        // 项目启动时，采集所有商品数据，限数据库没有任何商品记录时开启。
        if("1".equals(function.getFunctionSwitch())){
            List<DTKGood> list = dtkGoodService.gatherGoods();
            dtkGoodService.saveBatch(list);
            functionService.closeFunction(DTKConsts.DTK_FUNCTION_STARTUP_RUNNER_GATHER_GOODS);
        }
    }
}
