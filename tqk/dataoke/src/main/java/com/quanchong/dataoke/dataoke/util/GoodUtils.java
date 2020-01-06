package com.quanchong.dataoke.dataoke.util;

import com.quanchong.common.base.BaseGood;
import com.quanchong.common.util.ImageUtils;
import com.quanchong.common.util.QiNiuCloudUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class GoodUtils {

    /**
     * 批量删除
     * @param goodList
     */
    public static void removeImage(List<? extends BaseGood> goodList) {
        try{
            if(null!=goodList && !goodList.isEmpty()){
                List<String> fileNameList = new ArrayList<>();
                for(BaseGood good: goodList){
                    fileNameList.add(getImageFileName(good));
                }
                QiNiuCloudUtils.removeBatch(fileNameList.toArray(new String[fileNameList.size()]));
            }
        }catch (Exception e) {

        }
    }

    /**
     * 上传
     * @param goodList
     * @return
     */
    public static List<String> uploadImage(List<? extends BaseGood> goodList) {
        List<String> willUpdateGoodIds = new ArrayList<>();
        try{
            if(null!=goodList && !goodList.isEmpty()){
                List<String> fileNameList = new ArrayList<>();
                for(BaseGood good: goodList){
                    fileNameList.add(getImageFileName(good));
                }
                List<String> existsList = QiNiuCloudUtils.existsListBatch(fileNameList);
                for(BaseGood good: goodList){
                    String fileName = getImageFileName(good);
                    if(!existsList.contains(fileName)){
                        try{
                            QiNiuCloudUtils.uploadFromBytes(ImageUtils
                                    .compressImage(completeImageUrl(imageFieldValue(good)), 0.25f), fileName);
                            willUpdateGoodIds.add(fileName.split(".")[0]);
                        }catch (Exception e) {

                        }
                    }
                }
            }
        }catch (Exception e){
            return null;
        }
        return willUpdateGoodIds;
    }

    /**
     * 根据商品信息获取商品图片的上传后的名称
     * @param good
     * @return
     */
    public static String getImageFileName(BaseGood good) throws Exception{
        Class clazz = good.getClass();
        String imageUrl = imageFieldValue(good);
        Method method1 = clazz.getDeclaredMethod("getId");
        String goodId = method1.invoke(good).toString();
        return genImageFileName(imageUrl, goodId);
    }

    /**
     * 商品类对应图片字段的值
     * @param good
     * @return
     */
    public static String imageFieldValue(BaseGood good) throws Exception{
        Class<?> clazz = good.getClass();
        String imageFeildName = imageFieldName(clazz);
        String getMethodName = "get" + imageFeildName.substring(0, 1).toUpperCase() + imageFeildName.substring(1);
        Method method = clazz.getDeclaredMethod(getMethodName);
        return method.invoke(good).toString();
    }

    /**
     * 商品类对应图片字段名称
     * @param clazz
     * @return
     */
    public static String imageFieldName(Class<?> clazz) {
        String className = clazz.getName();
        String simpleName = className.substring(className.lastIndexOf(".")+1);
        if(simpleName.equals("DTKGood")){
            return "mainPic";
        }else if(simpleName.equals("FFQuanBrandGood")){
            return "pic";
        }else if(simpleName.equals("FFQuanDiscountGood")){
            return "pic";
        }else{
            throw new IllegalArgumentException("该对象不是BaseGood的派生类...");
        }
    }

    /**
     * 根据图片网络地址和提供图片名称，获取真正的图片名称
     * @param url 网络地址
     * @param fileName 名称
     * @return
     */
    public static String genImageFileName(String url, String fileName) {
        if(!StringUtils.isEmpty(url) && !StringUtils.isEmpty(fileName)) {
            String fileType = url.substring(url.lastIndexOf("."));
            return fileName + fileType;
        }
        return null;
    }

    /**
     * 补齐矫正 图片地址
     * @param url
     * @return
     */
    public static String completeImageUrl(String url) {
        if(!StringUtils.isEmpty(url)){
            if(url.startsWith("//")){
                url = "http://"+url.substring(2);
            }
            return url;
        }
        return null;
    }

    /**
     * 去重操作
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
