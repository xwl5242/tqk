package com.quanchong.server.job;

import com.quanchong.common.entity.TbCouponMaterial;
import com.quanchong.common.entity.TbGood;
import com.quanchong.server.feign.TbCouponMaterialFeignClient;
import com.quanchong.server.feign.TbGoodFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 淘宝商品job
 */
@Service
public class TbGoodsJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(TbGoodsJob.class);

    @Autowired
    private TbGoodFeignClient tbGoodFeignClient;

    @Autowired
    private TbCouponMaterialFeignClient tbCouponMaterialFeignClient;

    @Override
    @GlobalTransactional(name = "tqk_server_tb_goods_job", rollbackFor = RuntimeException.class)
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException, RuntimeException {
        log.info("淘宝客商品采集定时任务开始执行...");
        boolean optFlag = true;
        // 删除系统中已经存在的物料信息
        if(tbGoodFeignClient.queryCount() != 0){
            optFlag = optFlag && tbGoodFeignClient.removeAll();
        }
        // 查询需要默认采集的materialId
        Map<String, Object> cmParams = new HashMap<>();
        cmParams.put("default_collect", "1");
        cmParams.put("del_flag", "0");
        // 获取到所有默认需要采集的物料信息
        List<TbCouponMaterial> tbCouponMaterialList = tbCouponMaterialFeignClient.queryList(cmParams);
        // 遍历采集
        List<TbGood> tbItems = new ArrayList<>();
        tbCouponMaterialList.stream().forEach(tbCouponMaterial -> {
            // 淘宝官方默认物料有更新的记录数需要通过设置里的数量来采集，因为接口中pageSize最大为100
            for(long i=1; i<=tbCouponMaterial.getDefaultCollectNum()/40; i++){
                try {
                    // 调用淘宝客接口一次获取100条记录
                    List<TbGood> tbItemList = tbGoodFeignClient
                            .materialSelection(tbCouponMaterial.getMaterialId(), i, 40L);
                    // 持久化到数据库
                    tbItems.addAll(tbItemList);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
        optFlag = optFlag && tbGoodFeignClient.saveBatch(tbItems);
        if(!optFlag){
            throw new RuntimeException("采集失败");
        }
        log.info("淘宝客商品采集定时任务结束执行...");
    }

}
