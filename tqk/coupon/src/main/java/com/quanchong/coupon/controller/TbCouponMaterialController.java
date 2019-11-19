package com.quanchong.coupon.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.TbCouponMaterial;
import com.quanchong.coupon.service.TbCouponMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tb/coupon/material")
public class TbCouponMaterialController {

    @Autowired
    private TbCouponMaterialService tbCouponMaterialService;

    @GetMapping("/list")
    public List<TbCouponMaterial> queryList(@RequestParam Map<String, Object> params){
        return new ArrayList<>(tbCouponMaterialService.listByMap(params));
    }

    @GetMapping("/{parentId}")
    public List<TbCouponMaterial> queryListByParentId(@PathVariable("parentId") Integer parentId){
        QueryWrapper<TbCouponMaterial> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", "0").eq("parent_id", parentId);
        List<TbCouponMaterial> list = tbCouponMaterialService.list(wrapper);
        list.sort(Comparator.comparing(TbCouponMaterial::getSeq));
        return list;
    }

    @GetMapping("/siblings")
    public List<TbCouponMaterial> queryListBySibings(@RequestParam String materialId){
        // 目前 tb_coupon_material 表数据量不大，暂且这样查询
        List<TbCouponMaterial> list = tbCouponMaterialService.list(null);
        Map<String,Integer> map = list.stream().filter(tcm -> null!=tcm.getMaterialId())
                .collect(Collectors.toMap(TbCouponMaterial::getMaterialId, TbCouponMaterial::getParentId));
        Integer parentId = map.get(materialId);
        return list.stream().filter(tbCouponMaterial -> tbCouponMaterial.getParentId().equals(parentId))
                .sorted(Comparator.comparing(TbCouponMaterial::getSeq)).collect(Collectors.toList());
    }
}
