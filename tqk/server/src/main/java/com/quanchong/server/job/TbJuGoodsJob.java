package com.quanchong.server.job;

import com.quanchong.server.feign.TbJuItemFeignClient;
import com.quanchong.server.feign.TbJuTqgItemFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * 淘宝聚划算和淘抢购job
 */
@Service
public class TbJuGoodsJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(TbGoodsJob.class);

    @Autowired
    private TbJuItemFeignClient tbJuItemFeignClient;

    @Autowired
    private TbJuTqgItemFeignClient tbJuTqgItemFeignClient;

    @Override
    @GlobalTransactional(name = "tqk_server_tb_ju_goods_job", rollbackFor = RuntimeException.class)
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException, RuntimeException {
        log.info("淘宝客聚划算/淘抢购商品采集定时任务开始执行...");
        boolean flag = true;
        try{
            if (tbJuItemFeignClient.queryCount() != 0) {
                flag = flag && tbJuItemFeignClient.removeAll();
            }
            flag = flag && tbJuItemFeignClient.doCollect();
            if(tbJuTqgItemFeignClient.queryCount() != 0){
                flag = flag && tbJuTqgItemFeignClient.removeAll();
            }
            flag = flag && tbJuTqgItemFeignClient.doCollect(null, null);
        }catch (Exception e){
            e.printStackTrace();
            log.error("淘宝客聚划算/淘抢购商品采集定时任务执行异常: {} ！！！",e);
            throw new RuntimeException(e);
        }
        if(!flag){
            throw new RuntimeException("采集没有成功！");
        }
        log.info("淘宝客聚划算/淘抢购商品采集定时任务结束执行...");
    }
}
