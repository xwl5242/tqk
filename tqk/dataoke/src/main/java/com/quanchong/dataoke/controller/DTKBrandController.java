package com.quanchong.dataoke.controller;

import com.quanchong.common.entity.service.DTKBrand;
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
}
