package com.quanchong.common.util;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@Slf4j
public class QiNiuCloudUtils {

    private static final String accessKey = "ycgACZKc8abPX1y7enubK_MdVOWBWfdT76o87D5E";
    private static final String secretKey = "BrezxgSURbFGGBzLpPpMkjwQu7yWy0wttim7ZuFr";
    private static final String bucket = "youtools";

    /**
     * 上传图片
     * @param file
     * @param fileName
     */
    public static void uploadFromFile(File file, String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream in;
        try{
            in = new BufferedInputStream(new FileInputStream(file));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len;
            while (-1 != (len = in.read(buffer, 0, bufSize))){
                out.write(buffer, 0, len);
            }
            uploadFromBytes(out.toByteArray(), fileName);
        } catch (Exception e) {
            log.error("上传图片到七牛云服务器失败!");
        }
    }

    /**
     * 上传图片
     * @param bytes
     * @param fileName
     */
    public static void uploadFromBytes(byte[] bytes, String fileName) {
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try{
            Response response = uploadManager.put(bytes, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("图片上传结果==> key:{},hash:{}", putRet.key, putRet.hash);
        } catch (Exception e) {
            log.error("上传图片到七牛云服务器失败!");
        }
    }

    /**
     * 删除图片
     * @param fileName
     */
    public static void remove(String fileName) {
        Configuration cfg = new Configuration(Zone.zone0());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (Exception ex) {
            log.error("删除图片失败!");
        }
    }

}
