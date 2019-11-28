package com.quanchong.coupon.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.common.entity.Evaluate;
import com.quanchong.common.entity.TbGood;
import com.quanchong.common.util.DateUtils;
import com.quanchong.coupon.service.TbGoodService;
import com.quanchong.coupon.top.TbkService;
import com.taobao.api.domain.NTbkShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品Controller
 * 所有以 tbk 开头的请求为调用tbkService获取商品信息，否则为查询数据库表信息
 */
@RestController
@RequestMapping("/tb/goods")
public class TbGoodController {

    @Autowired
    private TbkService tbkService;

    @Autowired
    private TbGoodService tbGoodService;

    /**
     * pc商品搜索功能
     * @param keyword 关键字
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/tbk/search/pc")
    public List<TbGood> searchPC(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
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
    @GetMapping("/tbk/search/wifi")
    public List<TbGood> searchWifi(String keyword, Long materialId, Long pageNo, Long pageSize) throws Exception{
        return tbkService.searchWifi(keyword, materialId, pageNo, pageSize);
    }

    /**
     * 查询商品相关推荐
     * @param cat 商品类目根id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/tbk/search/recs")
    public List<TbGood> searchRecs(String cat, Long pageNo, Long pageSize) throws Exception {
        return tbkService.searchRecs(cat, pageNo, pageSize);
    }

    /**
     * 淘宝客物料精选
     * @param materialId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/tbk/material/selection")
    public List<TbGood> materialSelection(String materialId, Long pageNo, Long pageSize) throws Exception{
        List<TbGood> tbGoods = tbkService.materialSelection(materialId, pageNo, pageSize);
        return tbGoods.stream().map(tbGood -> {
            tbGood.setMaterialId(materialId);
            return tbGood;
        }).collect(Collectors.toList());
    }

    /**
     * 商品详情，根据商品id查询详情，店铺名称和卖家id查询店铺详情
     * @param tbkGood 商品
     * @return
     * @throws Exception
     */
    @PostMapping("/tbk/detail")
    public TbGood queryDetail(@RequestBody TbGood tbkGood) throws Exception{
        Map<String, Object> extraMap = tbkService.extraMap(tbkGood.getItemId(),
                tbkGood.getShopTitle(), tbkGood.getSellerId(), true);
        tbkGood.setExtra(extraMap);
        return tbkGood;
    }

    /**
     * 查询总记录数count
     * @return
     */
    @GetMapping("/count")
    public long queryCount(){
        return tbGoodService.count();
    }

    /**
     * 删除所有
     * @return
     */
    @PostMapping("/removeAll")
    public boolean removeAll(){
        QueryWrapper<TbGood> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("id");
        return tbGoodService.remove(wrapper);
    }

    /**
     * 批量保存
     * @param tbGoodList 要保存的tbGood的集合
     * @return
     */
    @PostMapping("/saveBatch")
    public boolean saveBatch(@RequestBody List<TbGood> tbGoodList){
        return tbGoodService.saveBatch(tbGoodList);
    }

    /**
     * 搜索功能，根据物料id
     * @param materialId 物料id
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     * @throws Exception
     */
    @GetMapping("/{materialId}")
    public List<TbGood> queryByMaterialId(@PathVariable("materialId") String materialId,
                                          @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Page<TbGood> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<TbGood> wrapper = new QueryWrapper<>();
        wrapper.eq("material_id", materialId);
        wrapper.orderByDesc("volume");
        IPage<TbGood> pageList = tbGoodService.page(page, wrapper);
        return pageList.getRecords();
    }

    /**
     * 根据主键查询商品详情
     * @param id 主键
     * @param needImgs 是否需要查询商品的图片详情
     * @param needRecs 是否需要查询相关商品推荐
     * @return
     */
    @GetMapping("/detail")
    public TbGood queryById(@RequestParam String id, @RequestParam Boolean needImgs, @RequestParam Boolean needRecs) throws Exception{
        TbGood tbGood = tbGoodService.getById(id);
        tbGood.setCouponStartTime(DateUtils.formatDateTime(Long.valueOf(tbGood.getCouponStartTime())));
        tbGood.setCouponEndTime(DateUtils.formatDateTime(Long.valueOf(tbGood.getCouponEndTime())));
        Map<String, Object> extraMap = tbkService.extraMap(tbGood.getItemId(),
                tbGood.getShopTitle(), tbGood.getSellerId(), needImgs);
        tbGood.setExtra(extraMap);
        if(needRecs){
            List<TbGood> recs = tbkService.searchRecs(tbGood.getRootCategoryId(),1L, 10L);
            tbGood.setRecs(recs);
        }
        return tbGood;
    }

}
