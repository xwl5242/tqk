package com.quanchong.common.util;

public class StringUtil {

    public static String firstCharUpperCase(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String likeSetMethodName(String str) {
        return str.startsWith("set")? str: "set" + firstCharUpperCase(str);
    }

    public static String likeGetMethodName(String str) {
        return str.startsWith("get")? str: "get" + firstCharUpperCase(str);
    }

}
