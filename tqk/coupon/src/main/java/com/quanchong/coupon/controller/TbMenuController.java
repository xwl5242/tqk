package com.quanchong.coupon.controller;

import com.quanchong.common.entity.TbMenu;
import com.quanchong.coupon.service.TbMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tb/menu")
public class TbMenuController {

    @Autowired
    private TbMenuService tbMenuService;

    @GetMapping("/list/{menuType}")
    public List<TbMenu> list(@PathVariable String menuType){
        return tbMenuService.list().stream()
                .filter(tbMenu -> tbMenu.getDelFlag().equals("0") && tbMenu.getMenuType().equals(menuType))
                .collect(Collectors.toList());
    }
}
