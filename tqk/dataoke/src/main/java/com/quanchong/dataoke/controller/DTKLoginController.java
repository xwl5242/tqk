package com.quanchong.dataoke.controller;

import com.quanchong.common.util.DateUtils;
import com.quanchong.common.util.JwtUtils;
import com.quanchong.dataoke.entity.DTKUser;
import com.quanchong.dataoke.miniprogram.MiniProgramService;
import com.quanchong.dataoke.miniprogram.entity.AuthResp;
import com.quanchong.dataoke.service.DTKUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dtk")
public class DTKLoginController {

    @Autowired
    private MiniProgramService miniProgramService;

    @Autowired
    private DTKUserService dtkUserService;

    @PostMapping("/login")
    public JwtUtils.Token login(@RequestBody DTKUser user){
        AuthResp authResp = miniProgramService.auth(user.getCode());
        user.setOpenId(authResp.getOpenId());
        user.setCreateTime(DateUtils.now());
        boolean saveRet = dtkUserService.save(user);
        return JwtUtils.createToken(authResp.getOpenId());
    }
}
