package com.quanchong.dataoke.miniprogram;

import com.alibaba.fastjson.JSONObject;
import com.quanchong.common.util.BeanUtil;
import com.quanchong.dataoke.dataoke.util.HttpUtils;
import com.quanchong.dataoke.miniprogram.entity.AuthResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MiniProgramService {

    @Autowired
    private MiniProgramConfig config;

    private String AUTH = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";


    /**
     * 授权，获取openId
     * @param jsCode
     * @return
     */
    public AuthResp auth(String jsCode){
        AUTH = AUTH.replace("APPID", config.getAppId());
        AUTH = AUTH.replace("SECRET", config.getSecret());
        AUTH = AUTH.replace("JSCODE", jsCode);
        String resp  = HttpUtils.sendGet(AUTH, null);
        System.out.println(resp);
        if(resp.contains("errcode")&&!"0".equals(JSONObject.parseObject(resp).getString("errcode"))){
            return null;
        }else{
            return BeanUtil.jsonToBean(resp, AuthResp.class);
        }
    }

}
