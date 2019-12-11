package com.quanchong.dataoke.dataoke.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {

    public static <T> T jsonToBean(String jsonStr, Class<T> clazz){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return (T) jsonObject.toJavaObject(clazz);
    }

    public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        jsonArray.stream().forEach(ja->list.add(jsonToBean(ja.toString(),clazz)));
        return list;
    }

}
