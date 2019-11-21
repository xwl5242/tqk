package com.quanchong.coupon.top;

import com.quanchong.common.entity.TbGood;
import com.quanchong.common.util.StringUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.NTbkShop;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 淘宝客api包装
 */
@Slf4j
@Component
public class TbkService {

    /**
     * 淘宝客相关配置
     */
    @Autowired
    private TbkConfig tbkConfig;

    private TaobaoClient tbClient = null;

    /**
     * 初始化淘宝客客户端
     */
    @PostConstruct
    public void init(){
        tbClient = new DefaultTaobaoClient(tbkConfig.getServerUrl(),
                tbkConfig.getAppKey(), tbkConfig.getSecret());
    }

    /**
     * 搜索pc端商品
     * @param keyword 关键词
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    public List<TbGood> searchPC(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
        return this.search(keyword, null, pageNo, pageSize, 1L, null,
                TbkConsts.SEARCH_SORT_TK_TOTAL_SALES_DESC, materialId, true, null);
    }


    /**
     * 搜索无线端商品
     * @param keyword 关键词
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    public List<TbGood> searchWifi(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
        return this.search(keyword, null, pageNo, pageSize, 2L, null,
                TbkConsts.SEARCH_SORT_TK_TOTAL_SALES_DESC, materialId, true, null);
    }

    /**
     * 淘宝客-推广者-物料搜索
     * @param keyword 商品筛选-查询词
     * @param startDsr 商品筛选-店铺dsr评分
     * @param pageNo 第几页，默认1
     * @param pageSize 页大小，默认20,1~100
     * @param platform 链接形式：1：PC,2:无线,默认1
     * @param isTmall 商品筛选-是否是天猫商品
     * @param sort 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
     * @param materialId 物料id
     * @param hasCoupon 是否有优惠券
     * @param freeShipment 是否包邮
     * @return
     */
    public List<TbGood> search(String keyword, Long startDsr, Long pageNo, Long pageSize,
                               Long platform, Boolean isTmall, String sort, Long materialId,
                               Boolean hasCoupon, Boolean freeShipment) throws Exception{
        log.info("淘宝客物料搜索...keyword:{},startDsr:{},pageNo:{},pageSize:{},platform:{}," +
                "isTmall:{},sort:{},materialId:{},hasCoupon:{},freeShipment:{}",keyword, startDsr,
                pageNo, pageSize, platform, isTmall, sort, materialId, hasCoupon, freeShipment);
        Assert.notNull(keyword, "搜索关键字不能为null");
        Assert.state(pageSize >= 1 && pageSize <= 100, "页大小范围为:1~100");
        List<TbGood> result = new ArrayList<>();
        // 创建淘宝客物料搜索请求
        TbkDgMaterialOptionalRequest request = new TbkDgMaterialOptionalRequest();
        request.setQ(keyword);
        request.setStartDsr(startDsr);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setPlatform(platform);
        request.setIsTmall(isTmall);
        request.setSort(sort);
        request.setMaterialId(materialId);
        request.setHasCoupon(hasCoupon);
        request.setNeedFreeShipment(freeShipment);
        request.setAdzoneId(Long.valueOf(tbkConfig.getAdZoneId()));
        // 调用请求，获取结果
        TbkDgMaterialOptionalResponse resp = tbClient.execute(request);
        List<TbkDgMaterialOptionalResponse.MapData> list = resp.getResultList();
        if(null!=list && !list.isEmpty()){
            // 查询出物料，遍历转换为基本商品类，并放入list中返回
            // 由于该接口的返回结果中reverse_price为null,所以需要再次根据商品id(itemId)查询商品详细信息
            // 遍历查询结果，并将所有的商品的id单独存储到List中
            List<TbGood> goodList = new TbGood().transforList(list, "clickUrl", "url");
            // 匹配reversePrice值
            result = setItem(goodList);
        }
        return result;
    }


    /**
     * 根据物料id查询商品信息
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    public List<TbGood> materialSelection(String materialId, long pageNo, long pageSize) throws Exception{
        log.info("淘宝客物料查询(by 物料id)请求,物料Id:{},页码:{},页大小:{}", materialId, pageNo, pageSize);
        Assert.notNull(materialId, "物料id不能为null");
        List<TbGood> result = new ArrayList<>();
        // 物料搜索请求
        TbkDgOptimusMaterialRequest request = new TbkDgOptimusMaterialRequest();
        request.setAdzoneId(Long.valueOf(tbkConfig.getAdZoneId()));
        request.setMaterialId(Long.valueOf(materialId));
        request.setPageSize(pageSize);
        request.setPageNo(pageNo);
        // 请求结果
        TbkDgOptimusMaterialResponse resp = tbClient.execute(request);
        if(Optional.ofNullable(resp).isPresent()){
            // 类型转换
            List<TbkDgOptimusMaterialResponse.MapData> list = resp.getResultList();
            if(null!=list && !list.isEmpty()){
                List<TbGood> goodList = new TbGood().transforList(list);
                result = setItem(goodList);
            }
        }
        return result;
    }

    /**
     * 根据商品id（itemId）查询指定商品的详情
     * @param itemIds 多个以 , 分割
     * @return
     * @throws Exception
     */
    public List<TbGood> searchByItemIds(String itemIds) throws Exception{
        log.info("淘宝客物料查询(by 商品ids,多个id以','分隔),商品ids:{}", itemIds);
        Assert.state(!StringUtils.isEmpty(itemIds), "itemIds不能为空或null");
        Assert.isTrue(itemIds.split(",").length <= 40, "itemIds最多40个");
        List<TbGood> result = new ArrayList<>();
        // 构建淘宝客物料详情(简版)请求
        TbkItemInfoGetRequest request = new TbkItemInfoGetRequest();
        // 设置商品ids
        request.setNumIids(itemIds);
        // 发送请求
        TbkItemInfoGetResponse resp = tbClient.execute(request);
        if(Optional.ofNullable(resp).isPresent()){
            List<TbkItemInfoGetResponse.NTbkItem> list = resp.getResults();
            if(null!=list && !list.isEmpty()){
                result = new TbGood().transforList(list, "itemId", "numIid");
            }
        }
        return result;
    }

    /**
     * 查询商铺信息
     * @param shopTitle
     * @param sellerId
     * @return
     * @throws Exception
     */
    public NTbkShop searchShop(String shopTitle, long sellerId) throws Exception{
        log.info("商铺信息查询,shopTitle:{}，sellerId:{}", shopTitle, sellerId);
        Assert.state(!StringUtils.isEmpty(shopTitle), "商店名称不能为null");
        Assert.state(!StringUtils.isEmpty(sellerId), "卖家id不能为null");
        NTbkShop shop = new NTbkShop();
        // 商铺信息查询请求
        TbkShopGetRequest req = new TbkShopGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
        req.setQ(shopTitle);
        TbkShopGetResponse resp = tbClient.execute(req);
        if(!ObjectUtils.isEmpty(resp)){
            // 匹配对应的商铺信息，并类型转换
            List<NTbkShop> shopList = resp.getResults();
            if(null!=shopList && !shopList.isEmpty()){
                List<NTbkShop> tbkShops = shopList.stream()
                        .filter(nTbkShop -> nTbkShop.getUserId().equals(sellerId))
                        .collect(Collectors.toList());
                shop = !tbkShops.isEmpty()?tbkShops.get(0):null;
            }
        }
        return shop;
    }

    /**
     * 获取商品的额外信息
     * @param itemId 商品id
     * @param shopTitle 店铺名称
     * @param sellerId 卖家名称
     * @return
     * @throws Exception
     */
    public Map<String, Object> extraMap(String itemId, String shopTitle, long sellerId, boolean needPictDetail) throws Exception{
        Map<String, Object> extraMap = new HashMap<>();
        extraMap.put("shop", this.searchShop(shopTitle, sellerId));
        extraMap.put("pictDetails", TbkUtil.getTbkGoodDetail(itemId));
        return extraMap;
    }

    /**
     * 物料精选和物料搜索结果匹配商品查询结果，补充reversePrice的值
     * 需要补充其他值，只需在map中put对应的get和set方法的属性
     * @param goodList 需要补充的
     * @return
     * @throws Exception
     */
    private List<TbGood> setItem(List<TbGood> goodList) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("reservePrice", "reservePrice");
        return (List<TbGood>) match(goodList, "itemId", map);
    }

    /**
     * 使用前必看：此方法有局限性（慎用）
     * 主要是对物料搜索和物料精选相关接口查询结果补充值所用（例如：精选接口返回结果中没有reversePrice值）
     * @param list 只能是自定义的淘宝商品相关业务类（tb_item,tb_ju_item,tb_ju_tqg_item），不能是淘宝客api中的业务实体类
     * @param itemIdGetMethodName 自定义业务类获取商品id的get方法名称,"get"可以省略,如：getItemId/ItemId/itemId皆可
     * @param getAndSetMethodName get方法和set方法名称，规则同itemIdGetMethodName，
     *                            get方法一定是TbkGood类的get方法，set方法为list形参中具体的业务类中的set方法
     * @return
     * @throws Exception
     */
    private List<?> match(List<?> list, String itemIdGetMethodName, Map<String, String> getAndSetMethodName) throws Exception{
        List<String> itemIdList = new ArrayList<>();
        // 构建商品id集合
        for (Object o : list) {
            Class<?> clazz = o.getClass();
            // getItemId方法
            itemIdGetMethodName = StringUtil.likeGetMethodName(itemIdGetMethodName);
            // 反射获取itemId
            itemIdList.add(String.valueOf(clazz.getMethod(itemIdGetMethodName).invoke(o)));
        }
        // 将商品id集合List转为字符串，以','拼接
        String itemIds = String.join(",", itemIdList);
        // 请求根据商品id查询商品详情
        List<TbGood> itemList = this.searchByItemIds(itemIds);
        // 将查询出的商品详情List转为Map,Map只存储商品id和商品
        Map<String, TbGood> itemMap = itemList.stream().
                collect(Collectors.toMap(TbGood::getItemId, Function.identity()));
        // 遍历搜索查询结果，从Map中根据商品id获取对应的reversePrice并设值，返回
        for (Object o : list) {
            Class<?> clazz = o.getClass();
            // 遍历get和set方法名称map
            Set<Map.Entry<String, String>> methods = getAndSetMethodName.entrySet();
            for (Map.Entry<String, String> method: methods) {
                // 获取TbkGood类中的get方法名称
                String getMethodName = StringUtil.likeGetMethodName(method.getKey());
                // 获取itemMap中的商品信息
                TbGood tbkGood = itemMap.get(String.valueOf(clazz.getMethod(itemIdGetMethodName).invoke(o)));
                // 反射获取值
                String arg = String.valueOf(tbkGood.getClass().getMethod(getMethodName).invoke(tbkGood));
                // 反射再设置值
                String setMethodName = StringUtil.likeSetMethodName(method.getValue());
                Method setMethod = clazz.getMethod(setMethodName, String.class);
                setMethod.invoke(o, arg);
            }
        }
        return list;
    }

}
