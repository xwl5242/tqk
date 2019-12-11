package com.quanchong.dataoke.dataoke;

import com.alibaba.fastjson.JSONObject;
import com.quanchong.dataoke.dataoke.entity.*;
import com.quanchong.dataoke.dataoke.util.BeanUtil;
import com.quanchong.dataoke.dataoke.util.HttpUtils;
import com.quanchong.dataoke.dataoke.util.SignMD5Util;
import com.quanchong.dataoke.entity.DTKGood;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        String resp = execute(DTKConsts.DTK_API_GOOD_LIST, param);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToBean(resp, DTKGoodResp.class);
        }
        return null;
    }
    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    public List<DTKCategory> getSuperCategory() throws Exception{
        String resp = execute(DTKConsts.DTK_API_SUPER_CATEGORY_URL,null);
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
        String resp = execute(DTKConsts.DTK_API_ACTIVITY_URL, null);
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
        String resp = execute(DTKConsts.DTK_API_TOPIC_URL, null);
        if(!StringUtils.isEmpty(resp)){
            return BeanUtil.jsonToList(resp, DTKTopic.class);
        }
        return null;
    }

    /**
     * dtk Api基础调用
     * @param apiUrl api地址
     * @param paramMap 接口参数
     * @return api接口结果
     * @throws Exception
     */
    public String execute(String apiUrl, Map<String,String> paramMap) throws Exception{
        if(null==paramMap){
            paramMap = new HashMap<>();
        }
        // 传入默认的version 和 appKey，并做签名操作
        TreeMap<String,String> paraMap = new TreeMap<>();
//        paraMap.put("version",dtkConfig.getVersion());
//        paraMap.put("appKey",dtkConfig.getAppKey());
        paraMap.put("version","v1.1.0");
        paraMap.put("appKey","5de7822971d61");
        paraMap.putAll(paramMap);
        log.info("sign前："+paraMap);
//        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, dtkConfig.getAppSecret()));
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, "f883be12ba21303fe6236958d3f3ea31"));
        log.info("大淘客api请求参数：{}", paraMap);
        String resp = HttpUtils.sendGet(apiUrl, paraMap);
        log.info("大淘客api响应结果：{}", resp);
        // 处理响应结果
        JSONObject jsonObject = JSONObject.parseObject(resp);
        String code = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_CODE);
        String data = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_DATA);
        return DTKConsts.DTK_API_RESPONSE_SUCCESS.equals(code)?data:null;
    }

    public static void main(String[] args) throws Exception{
        DTKService service = new DTKService();
        DTKGoodResp resp = service.getGoods(null);
        List<DTKGood> list = new DTKGood().transforList(resp.getList(), "desc", "description");
        System.out.println(list);
    }
}
