package com.quanchong.dataoke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKUser;
import com.quanchong.common.util.AESUtils;
import com.quanchong.common.util.DateUtils;
import com.quanchong.common.util.JwtUtils;
import com.quanchong.dataoke.miniprogram.MiniProgramService;
import com.quanchong.dataoke.miniprogram.entity.AuthResp;
import com.quanchong.dataoke.service.DTKUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dtk")
public class DTKLoginController {

    @Autowired
    private MiniProgramService miniProgramService;

    @Autowired
    private DTKUserService dtkUserService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody DTKUser user){
        AuthResp authResp = miniProgramService.auth(user.getCode());
        user.setOpenId(authResp.getOpenId());
        user.setCreateTime(DateUtils.now());
        QueryWrapper<DTKUser> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", authResp.getOpenId());
        DTKUser loginUser = dtkUserService.getOne(wrapper);
        if(null == loginUser){
            boolean saveRet = dtkUserService.save(user);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("openId", authResp.getOpenId());
        result.put("token", JwtUtils.createToken(authResp.getOpenId()));
        return result;
    }

    @GetMapping("/access_token")
    public JwtUtils.Token accessToken(String openId){
        if(StringUtils.isEmpty(openId)){
            return null;
        }
        try{
            openId = AESUtils.aesDecrypt(openId, JwtUtils.JWT_APPID);
        }catch(Exception e){
            return null;
        }
        return JwtUtils.createToken(openId);
    }
}
