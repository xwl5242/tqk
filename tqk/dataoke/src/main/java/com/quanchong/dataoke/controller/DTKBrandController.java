package com.quanchong.dataoke.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanchong.common.entity.service.DTKBrand;
import com.quanchong.common.ffquan.FFQuanApi;
import com.quanchong.common.ffquan.FFQuanBrand;
import com.quanchong.dataoke.dataoke.DTKService;
import com.quanchong.dataoke.service.DTKBrandService;
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
    private DTKBrandService dtkBrandService;

    @Autowired
    private DTKService dtkService;

    @GetMapping("/list")
    public List<DTKBrand> list(){
        return dtkBrandService.list();
    }

    @PostMapping("/test_add_data")
    public void testAddData() throws Exception{
        List<DTKBrand> dtkBrands = dtkService.brandList("1", "100");
        dtkBrandService.saveBatch(dtkBrands);
    }

    /**
     * ffquan 品牌分类导航
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/category")
    public JSONArray brandCategoryList() throws Exception{
        return FFQuanApi.brandCategoryList();
    }

    /**
     * ffquan 品牌推荐
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/recs")
    public List<FFQuanBrand> brandRecsList() throws Exception{
        JSONArray jsonArray = FFQuanApi.brandRecsList();
        return jsonArray.toJavaList(FFQuanBrand.class);
    }

    /**
     * ffquan 热销品牌
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/hots")
    public List<FFQuanBrand> brandHotsList() throws Exception{
       JSONArray jsonArray = FFQuanApi.brandHotsList();
       return jsonArray.toJavaList(FFQuanBrand.class);
    }

    /**
     * ffquan 根据品牌分类id查询品牌商品信息
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/list")
    public List<FFQuanBrand> brandListByCategoryId(String typeId) throws Exception{
        JSONArray jsonArray = FFQuanApi.brandListByCategoryId(typeId);
        return jsonArray.toJavaList(FFQuanBrand.class);
    }

    /**
     * ffquan 品牌详情
     * @param brandId
     * @return
     * @throws Exception
     */
    @GetMapping("/ffquan/detail")
    public FFQuanBrand brandDetail(String brandId) throws Exception{
        JSONObject jsonObject = FFQuanApi.brandDetail(brandId);
        return jsonObject.toJavaObject(FFQuanBrand.class);
    }
}
