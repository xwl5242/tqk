package com.quanchong.common.ffquan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanchong.common.util.HttpUtils;
import com.quanchong.common.util.StringUtil;
import org.springframework.util.StringUtils;

public class FFQuanApi {

    /**
     * ffquan 品牌相关
     */
    private static final String BRAND_PIC = "http://cmsjapi.ffquan.cn/api/category/product/model-detail-by-model-id-new?entityId=4&modelId=14451&source=3";
    private static final String BRAND_CATEGORY = "http://cmsjapi.ffquan.cn/api/goods/category-brand-list";
    private static final String BRAND_RECS_LIST = "http://cmsjapi.ffquan.cn/api/tb-service/get-one-data";
    private static final String BRAND_HOTS_LIST = "http://cmsjapi.ffquan.cn/api/tb-service/get-two-data";
    private static final String BRAND_LIST_BY_CATEGORY_ID = "http://cmsjapi.ffquan.cn/api/tb-service/brand-list-by-category-id?typeId=";
    private static final String BRAND_DETAIL = "http://cmsjapi.ffquan.cn/api/tb-service/brand/detail-info?brandId=";

    /**
     * ffquan discounts相关(折上折)
     */
    private static final String DISCOUNT_CATEGORY = "http://cmsjapi.ffquan.cn/api/goods/super-discount/cate-list/v1";
    private static final String DISCOUNT_GOODS = "http://cmsjapi.ffquan.cn/api/goods/super-discount/goods-list/v1?cId=";


    /**
     * 获取首页品牌专区图片
     * @return
     * @throws Exception
     */
    public static JSONArray brandPic() throws Exception{
        String resp = execute(BRAND_PIC);
        if(!StringUtils.isEmpty(resp)){
            return JSONObject.parseObject(resp).getJSONArray("config");
        }
        return null;
    }

    /**
     * 获取品牌类目
     * @return
     * @throws Exception
     */
    public static JSONArray brandCategoryList() throws Exception{
        String resp = execute(BRAND_CATEGORY);
        if(!StringUtils.isEmpty(resp)){
            return JSONArray.parseArray(resp);
        }
        return null;
    }

    /**
     * 获取推荐品牌
     * @return
     * @throws Exception
     */
    public static JSONArray brandRecsList() throws Exception{
        String resp = execute(BRAND_RECS_LIST);
        if(!StringUtils.isEmpty(resp)){
            JSONObject jsonObject = JSONObject.parseObject(resp);
            return jsonObject.getJSONArray("brandDTOS");
        }
        return null;
    }

    /**
     * 获取热销品牌
     * @return
     * @throws Exception
     */
    public static JSONArray brandHotsList() throws Exception{
        String resp = execute(BRAND_HOTS_LIST);
        if(!StringUtils.isEmpty(resp)){
            JSONObject jsonObject = JSONObject.parseObject(resp);
            return jsonObject.getJSONArray("brandDTOS");
        }
        return null;
    }

    /**
     * 根据品牌类目id查询对应的品牌
     * @param typeId
     * @return
     * @throws Exception
     */
    public static JSONArray brandListByCategoryId(String typeId) throws Exception{
        String resp = execute(BRAND_LIST_BY_CATEGORY_ID + typeId);
        if(!StringUtils.isEmpty(resp)){
            return JSONArray.parseArray(resp);
        }
        return null;
    }

    /**
     * 品牌详情
     * @param brandId
     * @return
     * @throws Exception
     */
    public static JSONObject brandDetail(String brandId) throws Exception{
        String resp = execute(BRAND_DETAIL + brandId);
        if(!StringUtils.isEmpty(resp)){
            return JSONObject.parseObject(resp);
        }
        return null;
    }

    /**
     * 折上折商品菜单类目
     * @return
     * @throws Exception
     */
    public static JSONArray discountCategoryList() throws Exception {
        String resp = execute(DISCOUNT_CATEGORY);
        if(!StringUtils.isEmpty(resp)) {
            String jx = "{\"id\":0,\"title\":\"精选\"}";
            JSONObject jsonObject = JSONObject.parseObject(jx);
            JSONArray jsonArray = JSONArray.parseArray(resp);
            jsonArray.add(0, jsonObject);
            return jsonArray;
        }
        return null;
    }

    /**
     * 折上折商品
     * @param cId
     * @return
     * @throws Exception
     */
    public static JSONObject discountGoods(String cId) throws Exception {
        String resp = execute(DISCOUNT_GOODS + cId);
        if(!StringUtils.isEmpty(resp)){
            return JSONObject.parseObject(resp);
        }
        return null;
    }

    /**
     * 调用ffquan api
     * @param apiUrl api url
     * @return
     * @throws Exception
     */
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
