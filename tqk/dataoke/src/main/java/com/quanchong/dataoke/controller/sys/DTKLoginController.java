package com.quanchong.dataoke.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKUser;
import com.quanchong.common.util.AESUtils;
import com.quanchong.common.util.DateUtils;
import com.quanchong.common.util.JwtEnum;
import com.quanchong.common.util.JwtUtils;
import com.quanchong.dataoke.miniprogram.MiniProgramService;
import com.quanchong.dataoke.miniprogram.entity.AuthResp;
import com.quanchong.dataoke.miniprogram.entity.MiniProgramResp;
import com.quanchong.dataoke.service.sys.DTKUserService;
import io.jsonwebtoken.Jwt;
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
    public MiniProgramResp login(String code){
        MiniProgramResp resp;
        try{
            AuthResp authResp = miniProgramService.auth(code);
            if(null!=authResp){
                resp = success(JwtUtils.createToken(authResp.getOpenId()));
            }else{
                resp = fail("授权失败！");
            }
        }catch(Exception e){
            resp = fail(e.getMessage());
        }
        return resp;
    }

    @PostMapping("/save_user")
    public MiniProgramResp saveUser(DTKUser user, JwtUtils.Token token) {
        MiniProgramResp resp = null;
        JwtEnum je = JwtUtils.validateToken(token.getToken());
        if(JwtEnum.TOKEN.getCode().equals(je.getCode())){
            String tokenStr = je.getMsg();
            int index = tokenStr.indexOf("openId:");
            if(index > 0){
                // token中存在openId
                String openId = tokenStr.substring(index+7);
                QueryWrapper<DTKUser> wrapper = new QueryWrapper<>();
                wrapper.eq("open_id", openId);
                DTKUser u = dtkUserService.getOne(wrapper);
                boolean saveOrUpdate = false;
                if(null == u){
                    user.setCreateTime(DateUtils.now());
                    saveOrUpdate = dtkUserService.save(u);
                }else{
                    u.setUpdateTime(DateUtils.now());
                    saveOrUpdate = dtkUserService.updateById(u);
                }
                resp = saveOrUpdate?success(token):fail("更新会员信息失败!");
            }
        }
        return resp;
    }

    public MiniProgramResp success(JwtUtils.Token token) {
        MiniProgramResp resp = new MiniProgramResp();
        resp.setCode("0000");
        resp.setMsg("请求成功!");
        resp.setToken(token);
        return resp;
    }

    public MiniProgramResp fail(String msg) {
        MiniProgramResp resp = new MiniProgramResp();
        resp.setCode("1111");
        resp.setMsg(msg);
        return resp;
    }
}
