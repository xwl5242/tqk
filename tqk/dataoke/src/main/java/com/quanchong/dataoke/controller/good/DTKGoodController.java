package com.quanchong.dataoke.controller.good;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.common.entity.dtkResp.*;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanDiscountResp;
import com.quanchong.dataoke.dataoke.DTKConsts;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.dataoke.DTKSortEnum;
import com.quanchong.dataoke.service.good.DTKGoodService;
import com.quanchong.dataoke.top.TbkService;
import com.quanchong.dataoke.top.TbkUtil;
import com.taobao.api.response.TbkItemInfoGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 大淘客controller
 */
@RestController
@RequestMapping("/dtk/goods")
public class DTKGoodController {

    @Autowired
    private DTKService dtkService;

    @Autowired
    private TbkService tbkService;

    @Autowired
    private DTKGoodService dtkGoodService;

    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    @GetMapping("/super_category")
    public List<SuperCategoryResp> getSuperCategory() throws Exception{
        return dtkService.superCategoryList();
    }

    /**
     * 获取官方活动
     * @return
     * @throws Exception
     */
    @GetMapping("/activity")
    public List<ActivityResp> getActivity() throws Exception{
        return dtkService.activityList();
    }

    /**
     * 品牌活动
     * @return
     * @throws Exception
     */
    @GetMapping("/topic")
    public List<TopicResp> getTopic() throws Exception{
        List<TopicResp> topicList = dtkService.topicList();
        return topicList.stream().filter(dtkTopic -> dtkTopic.getBanner().size()!=0).collect(Collectors.toList());
    }

    /**
     * 获取搜索热词
     * @return
     * @throws Exception
     */
    @GetMapping("/hotWords")
    public String getHotWords() throws Exception{
        return dtkService.hotWordsList();
    }

    /**
     * 获取联想词
     * @param hotWords
     * @return
     * @throws Exception
     */
    @GetMapping("/suggestion")
    public JSONArray getSuggestion(@RequestParam String hotWords) throws Exception{
        return dtkService.suggestion(hotWords);
    }

    /**
     * 首页好货精选
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public List<DTKGood> queryList(Long pageNo, Long pageSize) {
        pageNo = null == pageNo ? 1L : pageNo;
        pageSize = null == pageSize ? 20L : pageSize;
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("is_expire", "0");
        wrapper.orderByDesc("daily_sales");
        wrapper.orderByDesc("commission_rate");
        return dtkGoodService.page(new Page<>(pageNo, pageSize), wrapper).getRecords();
    }

    /**
     * 根据类目id查询商品列表
     * @param cid
     * @return
     * @throws Exception
     */
    @GetMapping("/cid")
    public List<DTKGood> queryByCId(Long pageNo, Long pageSize, String cid, String sort) {
        pageNo = null == pageNo ? 1L : pageNo;
        pageSize = null == pageSize ? 20L : pageSize;
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        sort = StringUtils.isEmpty(sort)? DTKSortEnum.USE_COUPON_DESC.getCode() :sort;
        if(sort.equals(DTKSortEnum.USE_COUPON_DESC.getCode())){
            wrapper.orderByDesc("coupon_receive_num");
        }else if(sort.equals(DTKSortEnum.CTIME_DESC.getCode())){
            wrapper.orderByDesc("create_time");
        }else if(sort.equals(DTKSortEnum.RESUME_DESC.getCode())){
            wrapper.orderByDesc("month_sales");
        }else{
            wrapper.orderByDesc("actual_price");
        }
        wrapper.eq("nine", "0");
        wrapper.eq("rank", "0");
        wrapper.eq("is_expire", "0");
        IPage<DTKGood> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return dtkGoodService.page(page, wrapper).getRecords();
    }

    /**
     * 获取子类目商品
     * @param pageId 页码
     * @param pageSize 页大小
     * @param subCid 子类目id
     * @param sort 排序
     * @return
     * @throws Exception
     */
    @GetMapping("/subCid")
    public GoodResp queryBySubCid(String pageId, String pageSize, String subCid, String sort) throws Exception{
        pageId = StringUtils.isEmpty(pageId)?"1":pageId;
        pageSize = StringUtils.isEmpty(pageSize)?"10":pageSize;
        sort = StringUtils.isEmpty(sort)? DTKSortEnum.USE_COUPON_DESC.getCode() :sort;
        return dtkService.goods(pageId, pageSize, sort, "", subCid);
    }

    /**
     * 查询咚咚抢商品
     * @param roundTime 场次时间 时间戳，到秒
     * @return
     * @throws Exception
     */
    @GetMapping("/ddq")
    public List<DTKGood> queryByDDQ(Long roundTime) throws Exception{
        return dtkService.goodsByDDQ(roundTime);
    }

    /**
     * 9.9包邮商品
     * @param pageNo 页面
     * @param pageSize 页大小
     * @param nineCid 9.9包邮商品所属类目id
     * @return
     * @throws Exception
     */
    @GetMapping("/nine")
    public List<DTKGood> queryByNine(Long pageNo, Long pageSize, String nineCid){
        pageNo = null == pageNo ? 1L : pageNo;
        pageSize = null == pageSize ? 20L : pageSize;
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("is_expire", "0");
        wrapper.eq("nine", "1");
        wrapper.eq("nine_cid", nineCid);
        IPage<DTKGood> page = new Page<>(pageNo, pageSize);
        IPage<DTKGood> pageList = dtkGoodService.page(page, wrapper);
        return pageList.getRecords();
    }

    /**
     * 获取榜单商品
     * @param pageNo 页码
     * @param pageSize 页大小
     * @param rankType 榜单类型
     * @param isNewRanking 是否是新上榜商品（12小时内入榜）
     * @return
     */
    @GetMapping("/ranking")
    public List<DTKGood> queryByRanking(Long pageNo, Long pageSize,
                                        String rankType, String cid, String isNewRanking) throws Exception{
        if("1".equals(rankType)){
            return dtkService.goodsByRanking("1", cid);
        }
        pageNo = null == pageNo ? 1L : pageNo;
        pageSize = null == pageSize ? 20L : pageSize;
        isNewRanking = StringUtils.isEmpty(isNewRanking)?"0":isNewRanking;
        rankType = StringUtils.isEmpty(rankType)? DTKConsts.DTK_RANK_TYPE_NOW :rankType;
        QueryWrapper<DTKGood> wrapper = new QueryWrapper<>();
        wrapper.eq("is_expire", "0");
        wrapper.eq("rank", "1");
        wrapper.eq("rank_type", rankType);
        wrapper.eq("new_ranking_goods", isNewRanking);
        if(!StringUtils.isEmpty(cid)){
            wrapper.eq("cid", cid);
        }
        wrapper.orderByAsc("ranking");
        IPage<DTKGood> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        IPage<DTKGood> pageList = dtkGoodService.page(page, wrapper);
        return pageList.getRecords();
    }

    /**
     * 查询品牌商品
     * @param pageId 页面
     * @param pageSize 页大小
     * @param brandIds 品牌id
     * @return
     * @throws Exception
     */
    @GetMapping("/byBrand")
    public List<DTKGood> queryByBrandId(String pageId, String pageSize, String brandIds) throws Exception{
        Assert.notNull(brandIds, "请填写品牌id");
        Map<String,String> map = new HashMap<>();
        map.put("brand", "1");
        map.put("brandIds", brandIds);
        map.put("pageId", StringUtils.isEmpty(pageId)?"1":pageId);
        map.put("pageSize", StringUtils.isEmpty(pageSize)?"20":pageSize);
        GoodResp resp = dtkService.goodsByMap(map);
        if(null!=resp){
            return resp.getList();
        }
        return new ArrayList<>();
    }

    /**
     * 超级搜索
     * @param pageId 页码id
     * @param pageSize 页大小
     * @param type 搜索类型
     * @param keyWords 关键词
     * @param tmall 是否是天猫商品
     * @param haitao 是否是海淘商品
     * @param sort 排序
     * @return
     * @throws Exception
     */
    @GetMapping("/super_search")
    public GoodResp searchFromSuperList(String type, String pageId, String pageSize,
                                        String keyWords, String tmall, String haitao, String sort) throws Exception{
        return dtkService.goodsBySuperSearch(type, pageId, pageSize, keyWords, tmall, haitao, sort);
    }

    /**
     * 相似商品查询
     * @param itemId 大淘客商品id
     * @param size 查询结果记录数
     * @return
     * @throws Exception
     */
    @GetMapping("/similer")
    public List<DTKGood> searchFromSimilerList(String itemId, String size) throws Exception{
        return dtkService.goodsBySimiler(itemId, size);
    }

    /**
     * 获取商品详情
     * @param id 商品id
     * @return
     */
    @GetMapping("/detail")
    public DTKGood queryById(@RequestParam String id, @RequestParam String goodsId) throws Exception{
        DTKGood dtkGood = dtkService.goodDetail(id, goodsId);
        try{
            if(null == dtkGood) {
                dtkGood = dtkGoodService.getById(id);
                if(null!=dtkGood){
                    Map<String,Object> extra = TbkUtil.getTbkGoodDetail(goodsId);
                    dtkGood.setDetailPics(extra.get("imgs").toString());
                    List<TbkItemInfoGetResponse.NTbkItem> items = tbkService.getTBKGoodDetail(goodsId);
                    if(null!=items && !items.isEmpty()){
                        dtkGood.setImgs(items.get(0).getSmallImages().toString());
                    }
                }
            }
        }catch(Exception e){
            dtkGood = null;
        }
        return dtkGood;
    }

    /**
     * 根据商品id置为失效状态
     * @param dtkGood
     */
    @PostMapping("/expire")
    public void expireGood(@RequestBody DTKGood dtkGood) {
        dtkGood.setIsExpire("1");
        dtkGoodService.updateById(dtkGood);
    }
}
