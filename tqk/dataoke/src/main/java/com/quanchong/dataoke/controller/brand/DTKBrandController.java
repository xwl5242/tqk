package com.quanchong.dataoke.controller.brand;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanchong.common.entity.service.DTKBrand;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanBrand;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.service.brand.DTKBrandService;
import com.quanchong.dataoke.service.brand.DTKFFQBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dtk/brand")
public class DTKBrandController {

    @Autowired
    private DTKFFQBrandService dtkffqBrandService;

    @Autowired
    private DTKService dtkService;

    /**
     * ffquan 品牌图片
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/pic")
    public JSONArray brandPic() throws Exception {
        return FFQuanApi.brandPic();
    }

    /**
     * ffquan 品牌分类导航
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/category")
    public JSONArray brandCategoryList() throws Exception {
        return FFQuanApi.brandCategoryList();
    }

    /**
     * ffquan 品牌推荐
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/recs")
    public List<FFQuanBrand> brandRecsList() {
        QueryWrapper<FFQuanBrand> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_type", "recs");
        return dtkffqBrandService.list(wrapper);
    }

    /**
     * ffquan 热销品牌
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/hots")
    public List<FFQuanBrand> brandHotsList(Long pageNo, Long pageSize) {
        pageNo = null == pageNo? 0L: pageNo;
        pageSize = null == pageSize? 0L: pageSize;
        QueryWrapper<FFQuanBrand> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_type", "hots");
        IPage<FFQuanBrand> pageList = dtkffqBrandService.page(new Page<>(pageNo, pageSize), wrapper);
        return pageList.getRecords();
    }

    /**
     * ffquan 根据品牌分类id查询品牌商品信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/list")
    public List<FFQuanBrand> brandListByCategoryId(String typeId, Long pageNo, Long pageSize) throws Exception{
        JSONArray jsonArray = FFQuanApi.brandListByCategoryId(typeId);
        return jsonArray.toJavaList(FFQuanBrand.class);
    }

    /**
     * ffquan 品牌详情
     *
     * @param brandId
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/detail")
    public FFQuanBrand brandDetail(String brandId) throws Exception {
        JSONObject jsonObject = FFQuanApi.brandDetail(brandId);
        return jsonObject.toJavaObject(FFQuanBrand.class);
    }
}
