package com.quanchong.dataoke.dataoke;

import com.alibaba.fastjson.JSONObject;
import com.quanchong.dataoke.dataoke.entity.DTKCategory;
import com.quanchong.dataoke.dataoke.util.BeanUtil;
import com.quanchong.dataoke.dataoke.util.HttpUtils;
import com.quanchong.dataoke.dataoke.util.SignMD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
@Slf4j
public class DTKService {

    @Autowired
    private DTKConfig dtkConfig;

    /**
     * 超级分类
     * @return
     * @throws Exception
     */
    public DTKCategory getSuperCategory() throws Exception{
        String resp = execute(DTKConsts.DTK_API_SUPER_CATEGORY_URL,null);
        if(!StringUtils.isEmpty(resp)){
            return (DTKCategory) BeanUtil.jsonToBean(resp, DTKCategory.class);
        }
        return null;
    }

    /**
     * dtk Api基础调用
     * @param apiUrl api地址
     * @param paramMap 接口参数
     * @return api接口结果
     * @throws Exception
     */
    public String execute(String apiUrl, Map<String,String> paramMap) throws Exception{
        if(null==paramMap){
            paramMap = new HashMap<>();
        }
        // 传入默认的version 和 appKey，并做签名操作
        TreeMap<String,String> paraMap = new TreeMap<>();
        paraMap.put("version",dtkConfig.getVersion());
        paraMap.put("appKey",dtkConfig.getAppKey());
        paraMap.putAll(paramMap);
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, dtkConfig.getAppSecret()));
        String resp = HttpUtils.sendGet(apiUrl, paraMap);
        // 处理响应结果
        JSONObject jsonObject = JSONObject.parseObject(resp);
        String code = jsonObject.getString(DTKConsts.DTK_API_RESPONSE_CODE);
        return DTKConsts.DTK_API_RESPONSE_SUCCESS.equals(code)?jsonObject.getString(DTKConsts.DTK_API_RESPONSE_DATA):null;
    }
}
