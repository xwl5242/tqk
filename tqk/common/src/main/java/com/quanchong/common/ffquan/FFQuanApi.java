package com.quanchong.common.ffquan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.quanchong.common.util.HttpUtils;
import org.springframework.util.StringUtils;

public class FFQuanApi {

    private static final String BRAND_CATEGORY = "http://cmsjapi.ffquan.cn/api/goods/category-brand-list";
    private static final String BRAND_RECS_LIST = "http://cmsjapi.ffquan.cn/api/tb-service/get-one-data";
    private static final String BRAND_HOTS_LIST = "http://cmsjapi.ffquan.cn/api/tb-service/get-two-data";
    private static final String BRAND_LIST_BY_CATEGORY_ID = "http://cmsjapi.ffquan.cn/api/tb-service/brand-list-by-category-id?typeId=";
    private static final String BRAND_DETAIL = "http://cmsjapi.ffquan.cn/api/tb-service/brand/detail-info?brandId=";


    public static JSONArray brandCategoryList() throws Exception{
        String resp = execute(BRAND_CATEGORY);
        if(!StringUtils.isEmpty(resp)){
            return JSONArray.parseArray(resp);
        }
        return null;
    }

    public static JSONArray brandRecsList() throws Exception{
        String resp = execute(BRAND_RECS_LIST);
        if(!StringUtils.isEmpty(resp)){
            JSONObject jsonObject = JSONObject.parseObject(resp);
            return jsonObject.getJSONArray("brandDTOS");
        }
        return null;
    }

    public static JSONArray brandHotsList() throws Exception{
        String resp = execute(BRAND_HOTS_LIST);
        if(!StringUtils.isEmpty(resp)){
            JSONObject jsonObject = JSONObject.parseObject(resp);
            return jsonObject.getJSONArray("brandDTOS");
        }
        return null;
    }

    public static JSONArray brandListByCategoryId(String typeId) throws Exception{
        String resp = execute(BRAND_LIST_BY_CATEGORY_ID + typeId);
        if(!StringUtils.isEmpty(resp)){
            return JSONArray.parseArray(resp);
        }
        return null;
    }

    public static JSONObject brandDetail(String brandId) throws Exception{
        String resp = execute(BRAND_DETAIL + brandId);
        if(!StringUtils.isEmpty(resp)){
            return JSONObject.parseObject(resp);
        }
        return null;
    }

    public static String execute(String apiUrl) throws Exception{
        String resp = HttpUtils.sendGet(apiUrl);
        if(!StringUtils.isEmpty(resp)){
            JSONObject respJson = JSONObject.parseObject(resp);
            String code = respJson.getString("code");
            if("0".equals(code)){
                return respJson.getString("data");
            }
        }
        return null;
    }
}
