package com.quanchong.dataoke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.common.entity.dtkResp.ActivityResp;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.dtkResp.SuperCategoryResp;
import com.quanchong.common.entity.dtkResp.TopicResp;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.dataoke.DTKSortEnum;
import com.quanchong.dataoke.service.DTKGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
     * 测试新增商品到数据库
     */
    @PostMapping("/create_test_data")
    public void createTestData(){
        dtkGoodService.createTestData();
    }

    /**
     * 根据类目id查询商品列表
     * @param cid
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public List<DTKGood> queryByCId(@RequestParam String cid, @RequestParam String sort) throws Exception{
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
        wrapper.eq("is_expire", "0");
        return dtkGoodService.list(wrapper);
    }

    @GetMapping("/brand/test")
    public List<DTKGood> test() throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("brand", "1");
        map.put("brandIds", "29504");
        map.put("pageSize", "2");
        return dtkService.goodsByMap(map).getList();
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
     * 获取商品list
     * @param pageNo 页面
     * @param pageSize 页大小
     * @return
     */
    @GetMapping("/page")
    public List<DTKGood> findPageList(Long pageNo, Long pageSize){
        IPage<DTKGood> page = new Page<>();
        page.setCurrent(ObjectUtils.isEmpty(pageNo)?1L:pageNo);
        page.setSize(ObjectUtils.isEmpty(pageSize)?50L:pageSize);
        IPage<DTKGood> goodPage = dtkGoodService.page(page);
        return goodPage.getRecords();
    }

    /**
     * 获取商品详情
     * @param id 商品id
     * @return
     */
    @GetMapping("/detail")
    public DTKGood queryById(@RequestParam String id) throws Exception{
        return dtkService.goodDetail(id);
    }

}
