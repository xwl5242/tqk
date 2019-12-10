package com.quanchong.dataoke.dataoke.util;

import com.alibaba.fastjson.JSONObject;

public class BeanUtil {

    public static Object jsonToBean(String jsonStr, Class<?> clazz){
       return JSONObject.parseObject(jsonStr, clazz);
    }
}
