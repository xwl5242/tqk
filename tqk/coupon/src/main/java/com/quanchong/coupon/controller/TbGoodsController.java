package com.quanchong.coupon.controller;

import com.quanchong.coupon.top.TbkGood;
import com.quanchong.coupon.top.TbkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 淘宝客Controller
 */
@RestController
@RequestMapping("/tb/goods")
public class TbGoodsController {

    @Autowired
    private TbkService tbkService;

    /**
     * pc商品搜索功能
     * @param keyword 关键字
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/search/pc")
    public List<TbkGood> searchPC(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
        return tbkService.searchPC(keyword, materialId, pageNo, pageSize);
    }

    /**
     * 无线商品搜索功能
     * @param keyword 关键字
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/search/wifi")
    public List<TbkGood> searchWifi(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
        return tbkService.searchWifi(keyword, materialId, pageNo, pageSize);
    }

    /**
     * 搜索功能，根据物料id
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/material/selection")
    public List<TbkGood> queryByMaterialId(@RequestParam("materialId") String materialId,
                                          @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) throws Exception{
        List<TbkGood> goodList = tbkService.materialSelection(materialId, pageNo, pageSize);
        return goodList.stream().map(baseTBGood -> {
            baseTBGood.setMaterialId(materialId);
            return baseTBGood;
        }).collect(Collectors.toList());
    }

    /**
     * 商品详情，根据商品id查询详情，店铺名称和卖家id查询店铺详情
     * @param tbkGood 商品
     * @return
     * @throws Exception
     */
    @PostMapping("/detail")
    public TbkGood queryByItem(@RequestBody TbkGood tbkGood) throws Exception{
        Map<String, Object> extraMap = tbkService.extraMap(tbkGood.getItemId(), tbkGood.getShopTitle(), tbkGood.getSellerId());
        tbkGood.setExtraMap(extraMap);
        return tbkGood;
    }
}
