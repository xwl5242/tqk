package com.quanchong.dataoke.dataoke;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanchong.common.entity.dtkResp.ActivityResp;
import com.quanchong.common.entity.dtkResp.SuperCategoryResp;
import com.quanchong.common.entity.dtkResp.GoodResp;
import com.quanchong.common.entity.dtkResp.TopicResp;
import com.quanchong.common.entity.service.DTKApi;
import com.quanchong.common.entity.service.DTKBrand;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.entity.service.DTKGoodCoupon;
import com.quanchong.common.util.BeanUtil;
import com.quanchong.common.util.DateUtils;
import com.quanchong.dataoke.dataoke.util.HttpUtils;
import com.quanchong.dataoke.dataoke.util.SignMD5Util;
import com.quanchong.dataoke.service.DTKApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@Slf4j
public class DTKService {

    @Autowired
    private DTKConfig dtkConfig;

    @Autowired
    private DTKApiService dtkApiService;

    /**
     * 获取品牌信息
     * @param pageId
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<DTKBrand> brandList(String pageId, String pageSize) throws Exception{
        Map<String,String> param = new HashMap<>();
        param.put("pageId", StringUtils.isEmpty(pageId)?"1":pageId);
        param.put("pageSize", StringUtils.isEmpty(pageSize)?"20":pageSize);
        String resp = execute(DTKConsts.DTK_API_KEY_BRAND_LIST,param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKBrand.class);
        }
        return null;
    }


    /**
     * 查询热搜记录
     * @return
     */
    public String hotWordsList() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_HOT_WORDS, null);
        if(!StringUtils.isEmpty(resp)){
            return resp;
        }
        return null;
    }

    /**
     * 咚咚抢商品
     * @param roundTime 场次时间（默认为当前场次，场次时间输入方式：yyyy-mm-dd hh:mm:ss）
     * @return
     * @throws Exception
     */
    public List<DTKGood> goodsByDDQ(Long roundTime) throws Exception{
        Map<String, String> param = new HashMap<>();
        if(null!=roundTime){
            String rDateTime = DateUtils.formatDateTime(roundTime);
            // 将空格转为url编码 %20
            rDateTime = rDateTime.replace(" ", "%20");
            param.put("roundTime", rDateTime);
        }
        String resp = execute(DTKConsts.DTK_API_KEY_GOODS_LIST_DDQ, param);
        if(!StringUtils.isEmpty(resp)){
            JSONArray jsonArray = JSONObject.parseObject(resp).getJSONArray("goodsList");
            return BeanUtil.jsonToList(jsonArray.toJSONString(), DTKGood.class);
        }
        return null;
    }

    /**
     * 9.9包邮商品
     * @param pageId 分页id
     * @param pageSize 页大小
     * @param nineCid 9.9包邮商品所属类型id
     * @return
     * @throws Exception
     */
    public GoodResp goodsByNine(String pageId, String pageSize, String nineCid) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("pageId", StringUtils.isEmpty(pageId)?"1":pageId);
        param.put("pageSize", StringUtils.isEmpty(pageSize)?"20":pageSize);
        param.put("nineCid", StringUtils.isEmpty(nineCid)?"0":nineCid);
        String resp = execute(DTKConsts.DTK_API_KEY_GOODS_LIST_99, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, GoodResp.class);
        }
        return null;
    }

    /**
     * 榜单商品
     * @param rankType 榜单类型，1：实时榜；2：全天榜
     * @return
     * @throws Exception
     */
    public List<DTKGood> goodsByRanking(String rankType, String cid) throws Exception{
        Map<String, String> param = new HashMap<>();
        param.put("rankType", StringUtils.isEmpty(rankType)?"1":rankType);
        if(!StringUtils.isEmpty(cid)){
            param.put("cid", cid);
        }
        String resp = execute(DTKConsts.DTK_API_KEY_RANKING_LIST, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKGood.class);
        }
        return null;
    }

    /**
     * 相似商品推荐查询
     * @param itemId 大淘客商品id
     * @param size 查询结果记录数
     * @return
     * @throws Exception
     */
    public List<DTKGood> goodsBySimiler(String itemId, String size) throws Exception{
        if(StringUtils.isEmpty(itemId)){
            throw new Exception("请填写大淘客商品id(itemId)");
        }
        Map<String,String> param = new HashMap<>();
        param.put("id", itemId);
        param.put("size", StringUtils.isEmpty(size)?"10":size);
        String resp = execute(DTKConsts.DTK_API_KEY_SIMILER_GOODS, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKGood.class);
        }
        return null;
    }

    /**
     * 超级搜索商品
     * @param type 搜索类型，0-综合结果，1-大淘客商品，2-联盟商品
     * @param pageId 请求页码，默认1
     * @param pageSize 每页条数，默认20，最大100
     * @param keyWords 关键词
     * @param tmall 是否天猫商品，0-所有商品，默认0
     * @param haitao 是否海淘商品，0-所有商品，默认0
     * @param sort 排序字段，total_sales(销量),price(价格);排序 _des(降序) _asc(升序)
     * @return
     * @throws Exception
     */
    public GoodResp goodsBySuperSearch(String type, String pageId, String pageSize,
                                        String keyWords, String tmall, String haitao, String sort) throws Exception{
        if(StringUtils.isEmpty(keyWords)){
            throw new Exception("请填写搜索关键词");
        }
        Map<String,String> param = new HashMap<>();
        param.put("pageSize", StringUtils.isEmpty(pageSize)?"20":pageSize);
        param.put("pageId", StringUtils.isEmpty(pageId)?"1":pageId);
        if(!StringUtils.isEmpty(sort)){
            param.put("sort", sort);
        }
        param.put("type", StringUtils.isEmpty(type)?"0":type);
        param.put("tmall", StringUtils.isEmpty(tmall)?"0":tmall);
        param.put("haitao", StringUtils.isEmpty(haitao)?"0":haitao);
        param.put("keyWords", keyWords);
        String resp = execute(DTKConsts.DTK_API_KEY_LIST_SUPER_GOODS, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, GoodResp.class);
        }
        return null;
    }

    /**
     * 获取商品
     * @param pageId 分页id
     * @param pageSize 页大小
     * @param sort 排序方式
     * @param cids 一级类目id
     * @param subcid 二级类目id
     * @return
     * @throws Exception
     */
    public GoodResp goods(String pageId, String pageSize, String sort, String cids, String subcid) throws Exception{
        return goodsByKeys(pageId, pageSize, sort, cids, subcid,
                "", "", "", "", "", "", "", "");
    }

    /**
     * 获取商品
     * @param pageSize 每页条数
     * @param pageId 分页id
     * @param sort 排序方式
     * @param cids 一级类目id
     * @param subcid 二级类目id
     * @param juHuaSuan 是否聚划算
     * @param taoQiangGou 是否淘抢购
     * @param tmall 是否天猫
     * @param tchaoshi 是否天猫超时
     * @param goldSeller 是否金牌卖家
     * @param haitao 是否海涛
     * @param brand 是否品牌
     * @param brandIds 品牌id
     * @return
     * @throws Exception
     */
    public GoodResp goodsByKeys(String pageId, String pageSize, String sort, String cids, String subcid,
                             String juHuaSuan, String taoQiangGou, String tmall, String tchaoshi, String goldSeller,
                             String haitao, String brand, String brandIds) throws Exception{
        Map<String,String> param = new HashMap<>();
        param.put("pageSize", StringUtils.isEmpty(pageSize)?"50":pageSize);
        param.put("pageId", StringUtils.isEmpty(pageId)?"1":pageId);
        param.put("sort", StringUtils.isEmpty(sort)?DTKSortEnum.ALL.getCode():sort);
        if(!StringUtils.isEmpty(cids)){
            param.put("cids", cids);
        }
        if(!StringUtils.isEmpty(subcid)){
            param.put("subcid", subcid);
        }
        param.put("juHuaSuan", StringUtils.isEmpty(juHuaSuan)?"0":juHuaSuan);
        param.put("taoQiangGou", StringUtils.isEmpty(taoQiangGou)?"0":taoQiangGou);
        param.put("tmall", StringUtils.isEmpty(tmall)?"0":tmall);
        param.put("tchaoshi", StringUtils.isEmpty(tchaoshi)?"0":tchaoshi);
        param.put("goldSeller", StringUtils.isEmpty(goldSeller)?"0":goldSeller);
        param.put("haitao", StringUtils.isEmpty(haitao)?"0":haitao);
        param.put("brand", StringUtils.isEmpty(brand)?"0":brand);
        if(!StringUtils.isEmpty(brandIds)){
            param.put("brandIds", brandIds);
        }
        return goodsByMap(param);
    }

    /**
     * 获取商品
     * @param param api参数
     * @return
     * @throws Exception
     */
    public GoodResp goodsByMap(Map<String,String> param) throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_GOODS_LIST, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, GoodResp.class);
        }
        return null;
    }

    /**
     * 获取商品详情
     * @param id
     * @throws Exception
     */
    public DTKGood goodDetail(String id) throws Exception{
        Map<String,String> param = new HashMap<>();
        param.put("id", id);
        String resp = execute(DTKConsts.DTK_API_KEY_GOODS_DETAILS,param);
        if(!StringUtils.isEmpty(resp)){
            return  BeanUtil.jsonToBean(resp, DTKGood.class);
        }
        return null;
    }

    /**
     * 高效转链接
     * @param goodsId
     * @return
     * @throws Exception
     */
    public DTKGoodCoupon privilegeLinkList(String goodsId) throws Exception {
        Map<String,String> param = new HashMap<>();
        param.put("goodsId", goodsId);
        String resp = execute(DTKConsts.DTK_API_KEY_PRIVILEGE_LINK, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, DTKGoodCoupon.class);
        }
        return null;
    }

    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    public List<SuperCategoryResp> superCategoryList() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_SUPER_CATEGORY,null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, SuperCategoryResp.class);
        }
        return null;
    }

    /**
     * 活动
     * @return
     * @throws Exception
     */
    public List<ActivityResp> activityList() throws Exception {
        String resp = execute(DTKConsts.DTK_API_KEY_ACTIVITY, null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, ActivityResp.class);
        }
        return null;
    }

    /**
     * 精选专辑列表
     * @return
     * @throws Exception
     */
    public List<TopicResp> topicList() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_TOPIC, null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, TopicResp.class);
        }
        return null;
    }

    /**
     * dtk Api基础调用
     * @param apiKey api 标识
     * @param paramMap 接口参数
     * @return api接口结果
     * @throws Exception
     */
    public String execute(String apiKey, Map<String,String> paramMap) throws Exception{
        if(null==paramMap){
            paramMap = new HashMap<>();
        }
        DTKApi dtkApi = dtkApiService.getBYApiKey(apiKey);
        // 传入默认的version 和 appKey，并做签名操作
        TreeMap<String,String> paraMap = new TreeMap<>();
        paraMap.put("version",dtkApi.getApiVersion());
        paraMap.put("appKey",dtkConfig.getAppKey());
        paraMap.putAll(paramMap);
        log.info("sign前："+paraMap);
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, dtkConfig.getAppSecret()));
        String resp = HttpUtils.sendGet(dtkApi.getApiUrl(), paraMap);
        // 处理响应结果
        JSONObject jsonObject = JSONObject.parseObject(resp);
        String code = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_CODE);
        String data = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_DATA);
        return DTKConsts.DTK_API_RESPONSE_SUCCESS.equals(code)?data:null;
    }

}
