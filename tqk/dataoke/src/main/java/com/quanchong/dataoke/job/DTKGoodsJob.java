package com.quanchong.dataoke.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.dtkResp.GoodStaleResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.ffquan.FFQuanBrand;
import com.quanchong.common.ffquan.FFQuanBrandGood;
import com.quanchong.common.ffquan.FFQuanDiscountGood;
import com.quanchong.common.util.DateUtils;
import com.quanchong.dataoke.dataoke.util.GoodUtils;
import com.quanchong.dataoke.service.brand.DTKFFQBrandGoodService;
import com.quanchong.dataoke.service.brand.DTKFFQBrandService;
import com.quanchong.dataoke.service.good.DTKFFQDiscountGoodService;
import com.quanchong.dataoke.service.good.DTKGoodService;
import com.quanchong.dataoke.service.sys.DTKFunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableScheduling
public class DTKGoodsJob {

    @Autowired
    private DTKGoodService dtkGoodService;

    @Autowired
    private DTKFFQBrandService dtkffqBrandService;

    @Autowired
    private DTKFFQBrandGoodService dtkffqBrandGoodService;

    @Autowired
    private DTKFFQDiscountGoodService dtkffqDiscountGoodService;

    @Autowired
    private DTKFunctionService dtkFunctionService;

    private Boolean openSchedule;

    @PostConstruct
    public void init() {
        openSchedule = "1".equals(dtkFunctionService.getFunction("gather_schedule")
                .getFunctionSwitch());
    }

    @Scheduled(fixedDelay = 15 * 1000)
    public void heart() {
        if(!openSchedule){
            init();
        }
    }

    /**
     * 每30分钟拉取一次商品数据
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 60 * 1000, fixedDelay=30*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByPull() throws Exception {
        if(openSchedule) {
            List<DTKGood> list = dtkGoodService.gatherGoodsByPull();
            log.info("每30分钟定时拉取商品数据,拉取记录条数:{}", null != list ? list.size() : 0);
            if (null != list && !list.isEmpty()) {
                dtkGoodService.saveOrUpdateBatch(list);
            }
        }
    }

    /**
     * 每10分钟拉取一次失效商品数据
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 2 * 1000, fixedDelay=10*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByStale() throws Exception {
        if(openSchedule) {
            List<GoodStaleResp.GoodStale> list = dtkGoodService.gatherGoodsByStale();
            log.info("每10分钟定时拉取失效商品数据,拉取记录条数:{}", null != list ? list.size() : 0);
            if (null != list && !list.isEmpty()) {
                List<DTKGood> goodList = list.stream().map(goodStale -> {
                    DTKGood good = new DTKGood();
                    good.setId(goodStale.getId());
                    good.setIsExpire("1");
                    return good;
                }).collect(Collectors.toList());
                dtkGoodService.updateBatchById(goodList);
            }
        }
    }

    /**
     * 每1个小时拉取一次商品变更数据
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 7*60*1000, fixedDelay=60*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByNewest() throws Exception {
        if(openSchedule) {
            List<DTKGood> list = dtkGoodService.gatherGoodsByNewest();
            log.info("每小时定时拉取最新商品数据,拉取记录条数:{}", null != list ? list.size() : 0);
            if (null != list && !list.isEmpty()) {
                dtkGoodService.updateBatchById(list);
            }
        }
    }

    /**
     * 每13分钟更新一次9.9包邮商品
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 10*60*1000, fixedDelay=13*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByNine() throws Exception {
        if(openSchedule) {
            List<DTKGood> list = dtkGoodService.gatherGoodsByNine();
            log.info("每13分钟定时拉取9.9商品数据,拉取记录条数:{}", null != list ? list.size() : 0);
            if (null != list && !list.isEmpty()) {
                dtkGoodService.saveOrUpdateBatch(list);
            }
        }
    }

    /**
     * 17分钟更新一次榜单商品
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 12*60*1000, fixedDelay=17*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByRanking() throws Exception {
        if(openSchedule) {
            List<DTKGood> list = dtkGoodService.gatherGoodsByRanking();
            log.info("每17分钟定时拉取榜单商品数据,拉取记录条数:{}", null != list ? list.size() : 0);
            if (null != list && !list.isEmpty()) {
                dtkGoodService.saveOrUpdateBatch(list);
            }
        }
    }

    /**
     * 每2个小时采集一下ffquan 品牌和品牌商品信息
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 3*60*1000, fixedDelay=2*60*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByFFQBrand() throws Exception {
        if(openSchedule) {
            Map<String, Object> result = dtkffqBrandService.gather();
            if (null != result) {
                List<FFQuanBrand> brands = (List<FFQuanBrand>) result.get("brands");
                List<FFQuanBrandGood> goods = (List<FFQuanBrandGood>) result.get("goods");
                log.info("每120分钟定时拉取品牌数据,拉取记录条数:{}", null != brands ? brands.size() : 0);
                log.info("每120分钟定时拉取品牌商品数据,拉取记录条数:{}", null != goods ? goods.size() : 0);
                if (null != brands && !brands.isEmpty()) {
                    List<FFQuanBrand> brandList = dtkffqBrandService.list();
                    if (null != brandList && !brandList.isEmpty()) {
                        QueryWrapper wrapper = new QueryWrapper();
                        wrapper.isNotNull("brand_id");
                        dtkffqBrandService.remove(wrapper);
                    }
                    dtkffqBrandService.saveBatch(brands);
                }
                if (null != goods && !goods.isEmpty()) {
                    List<FFQuanBrandGood> goodList = dtkffqBrandGoodService.list();
                    if (null != goodList && !goodList.isEmpty()) {
                        QueryWrapper wrapper = new QueryWrapper();
                        wrapper.isNotNull("id");
                        dtkffqBrandGoodService.remove(wrapper);
                        GoodUtils.removeImage(goodList);
                    }
                    dtkffqBrandGoodService.saveBatch(goods);
                }
            }
        }
    }

    /**
     * 每2个小时采集一下ffquan 折扣商品信息
     *
     * @throws Exception
     */
    @Scheduled(initialDelay = 2*60*1000, fixedDelay=2*65*60*1000)
    @Transactional(rollbackFor = Exception.class)
    public void gatherGoodsByDiscount() throws Exception {
        if(openSchedule) {
            List<FFQuanDiscountGood> goods = dtkffqDiscountGoodService.gather();
            log.info("每122分钟定时拉取折扣商品数据,拉取记录条数:{}", null != goods ? goods.size() : 0);
            if (null != goods && !goods.isEmpty()) {
                List<FFQuanDiscountGood> goodList = dtkffqDiscountGoodService.list();
                if (null != goodList && !goodList.isEmpty()) {
                    QueryWrapper wrapper = new QueryWrapper();
                    wrapper.isNotNull("id");
                    dtkffqDiscountGoodService.remove(wrapper);
                    GoodUtils.removeImage(goodList);
                }
                boolean save = dtkffqDiscountGoodService.saveBatch(goods);
            }
        }
    }

    /**
     * 每天凌晨2点清除失效的商品（物理删除）
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void removeIsExpireGoods() throws Exception {
        if(openSchedule) {
            log.info("每天凌晨2点清除失效商品");
            QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
            wrapper.eq("is_expire", "1");
            List<DTKGood> list = dtkGoodService.list(wrapper);
            dtkGoodService.remove(wrapper);
        }
    }

    /**
     * 每天凌晨1点将优惠券到期的商品置为失效
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void setExpireGoodsByCouponEndTime() throws Exception {
        if(openSchedule) {
            String now = DateUtils.now();
            QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
            wrapper.eq("is_expire", "0");
            wrapper.lt("coupon_end_time", now);
            List<DTKGood> expireGoods = dtkGoodService.list(wrapper);
            log.info("每天凌晨1点将优惠券到期商品置为失效,失效记录条数:{}", null != expireGoods ? expireGoods.size() : 0);
            if (null != expireGoods && !expireGoods.isEmpty()) {
                expireGoods = expireGoods.stream().map(dtkGood -> {
                    dtkGood.setIsExpire("1");
                    return dtkGood;
                }).collect(Collectors.toList());
                dtkGoodService.updateBatchById(expireGoods);
            }
        }
    }
}
