package com.quanchong.dataoke.dataoke;

import com.alibaba.fastjson.JSONObject;
import com.quanchong.dataoke.dataoke.entity.*;
import com.quanchong.dataoke.dataoke.util.BeanUtil;
import com.quanchong.dataoke.dataoke.util.HttpUtils;
import com.quanchong.dataoke.dataoke.util.SignMD5Util;
import com.quanchong.dataoke.entity.DTKApi;
import com.quanchong.dataoke.entity.DTKGood;
import com.quanchong.dataoke.entity.DTKGoodCoupon;
import com.quanchong.dataoke.service.DTKApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DTKService {

    @Autowired
    private DTKConfig dtkConfig;

    @Autowired
    private DTKApiService dtkApiService;

    /**
     * 查询热搜记录
     * @return
     */
    public String getHotWords() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_HOT_WORDS, null);
        if(!StringUtils.isEmpty(resp)){
            return resp;
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
    public DTKGoodResp searchFromSuperList(String type, String pageId, String pageSize,
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
            return BeanUtil.jsonToBean(resp, DTKGoodResp.class);
        }
        return null;
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
    public DTKGoodResp getGoods(String pageSize, String pageId, String sort, String cids, String subcid,
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
        return getGoods(param);
    }

    /**
     * 获取商品
     * @param param api参数
     * @return
     * @throws Exception
     */
    public DTKGoodResp getGoods(Map<String,String> param) throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_GOODS_LIST, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, DTKGoodResp.class);
        }
        return null;
    }

    /**
     * 获取商品详情
     * @param id
     * @throws Exception
     */
    public DTKGood getGoodDetail(String id) throws Exception{
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
    public DTKGoodCoupon getPrivilegeLink(String goodsId) throws Exception {
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
    public List<DTKCategory> getSuperCategory() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_SUPER_CATEGORY,null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKCategory.class);
        }
        return null;
    }

    /**
     * 活动
     * @return
     * @throws Exception
     */
    public List<DTKActivity> getActivity() throws Exception {
        String resp = execute(DTKConsts.DTK_API_KEY_ACTIVITY, null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKActivity.class);
        }
        return null;
    }

    /**
     * 精选专辑
     * @return
     * @throws Exception
     */
    public List<DTKTopic> getTopic() throws Exception{
        String resp = execute(DTKConsts.DTK_API_KEY_TOPIC, null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKTopic.class);
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
        log.info("大淘客api请求参数：{}", paraMap);
        String resp = HttpUtils.sendGet(dtkApi.getApiUrl(), paraMap);
        log.info("大淘客api响应结果：{}", resp);
        // 处理响应结果
        JSONObject jsonObject = JSONObject.parseObject(resp);
        String code = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_CODE);
        String data = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_DATA);
        return DTKConsts.DTK_API_RESPONSE_SUCCESS.equals(code)?data:null;
    }

    public static void main(String[] args) throws Exception{
        DTKService service = new DTKService();
//        DTKGoodResp resp = service.getGoods(null);
//        List<DTKGood> list = new DTKGood().transforList(resp.getList(), "desc", "description");
//        System.out.println(list);
        System.out.println(service.getPrivilegeLink("545384308841"));
    }
}
