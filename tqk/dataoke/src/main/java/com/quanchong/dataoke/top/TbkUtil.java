package com.quanchong.dataoke.top;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanchong.common.entity.Evaluate;
import com.quanchong.common.util.HttpUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class TbkUtil {

    /**
     * 淘宝商品详情请求地址，需要替换地址中的itemId为真实的id
     */
    private static final String TB_ITEM_DETAIL_URL =
            "http://acs.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?data=%7B%22itemNumId%22%3A%22@itemId%22%7D";


    /**
     * 获取商品的根类目id，可用于淘宝客商品搜索接口中cat字段，用来查询相关商品
     * @param itemId
     * @return
     */
    public static String getTbkGoodRootCategoryId(String itemId) {
        String rootCategoryId = "";
        try{
            String url = TB_ITEM_DETAIL_URL.replace("@itemId", itemId);
            // 发送请求，获取商品的详细信息数据
            String result = HttpUtils.sendGet(url);
            if (!ObjectUtils.isEmpty(result)) {
                JSONObject retJson = (JSONObject) JSON.parse(result);
                // 请求成功，分析请求结果，获取店铺和商品图片详情
                JSONObject dataJson = retJson.getJSONObject("data");
                if (!ObjectUtils.isEmpty(dataJson)) {
                    // 获取商品图片详情
                    JSONObject itemJson = dataJson.getJSONObject("item");
                    if (!ObjectUtils.isEmpty(itemJson)) {
                        // rootCategoryId
                        rootCategoryId = itemJson.getString("rootCategoryId");
                    }
                }
            }
        }catch (Exception e){

        }
        return rootCategoryId;
    }

    /**
     * 根据商品id获取商品的特殊详情信息：1.商品所属店铺的各项指标的评分 2.商品的图片详情
     * @param itemId 商品id
     * @return map，evaluates：店铺各项指标评分详细；itemImgs：商品图片详情
     */
    public static Map<String, Object> getTbkGoodDetail(String itemId){
        Map<String, Object> map = new HashMap<>();
        try{
            String url = TB_ITEM_DETAIL_URL.replace("@itemId", itemId);
            // 发送请求，获取商品的详细信息数据
            String result = HttpUtils.sendGet(url);
            if(!ObjectUtils.isEmpty(result)){
                JSONObject retJson = (JSONObject) JSON.parse(result);
                // 请求成功，分析请求结果，获取店铺和商品图片详情
                JSONObject dataJson = retJson.getJSONObject("data");
                if(!ObjectUtils.isEmpty(dataJson)){
                    // 获取商品图片详情
                    JSONObject itemJson = dataJson.getJSONObject("item");
                    if(!ObjectUtils.isEmpty(itemJson)){
                        // rootCategoryId
                        String rootCategoryId = itemJson.getString("rootCategoryId");
                        // 解析真正的图片地址
                        String itemImgsUrl = itemJson.getString("moduleDescUrl");
                        // 发送请求，获取图片
                        String imgsResult = HttpUtils.sendGet(itemImgsUrl);
                        if(!StringUtils.isEmpty(imgsResult)){
                            JSONObject imgsJson = (JSONObject) JSON.parse(imgsResult);
                            // 获取图片请求成功返回，解析图片地址信息
                            JSONArray imgs = imgsJson.getJSONObject("data").getJSONArray("children");
                            List<String> pictUrlList = new ArrayList<>();
                            for(int i=0;i<imgs.size();i++){
                                JSONObject imgObject = (JSONObject) imgs.get(i);
                                // ID凡是以detail_pic_开头的即为商品详情的具体图片地址
                                if(imgObject.getString("ID").startsWith("detail_pic_")){
                                    // 获取图片url
                                    String picUrl = imgObject.getJSONObject("params").getString("picUrl");
                                    pictUrlList.add(imgObject.getString("ID")+"@"+picUrl);
                                }
                            }
                            pictUrlList.stream().sorted(Comparator.comparing(s -> s.substring(0, s.indexOf('@'))));
                            map.put("imgs", pictUrlList);
                            map.put("rootCategoryId", rootCategoryId);
                        }
                    }
                    // 获取商品所属店铺
                    JSONObject sellerJson = dataJson.getJSONObject("seller");
                    if(!ObjectUtils.isEmpty(sellerJson)){
                        // 获取商品所属店铺的各项指标
                        JSONArray jsonArray = sellerJson.getJSONArray("evaluates");
                        if(null!=jsonArray && !jsonArray.isEmpty()){
                            // 遍历循环指标
                            List<Evaluate> evaluateList = new ArrayList<>();
                            for(int j=0;j<jsonArray.size();j++){
                                Evaluate evaluate = JSONObject.parseObject(jsonArray.get(j).toString(), Evaluate.class);
                                evaluateList.add(evaluate);
                            }
                            map.put("evaluates", evaluateList);
                        }
                    }
                }
            }

        }catch (Exception e){

        }
        return map;
    }

}
