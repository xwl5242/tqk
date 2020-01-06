package com.quanchong.common.util;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

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
    public static void uploadFromFile(File file, String fileName) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        int bufSize = 1024;
        byte[] buffer = new byte[bufSize];
        int len;
        while (-1 != (len = in.read(buffer, 0, bufSize))){
            out.write(buffer, 0, len);
        }
        uploadFromBytes(out.toByteArray(), fileName);
    }

    /**
     * 上传图片
     * @param bytes
     * @param fileName
     */
    public static void uploadFromBytes(byte[] bytes, String fileName) throws Exception{
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(bytes, fileName, upToken);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("图片上传结果==> key:{},hash:{}", putRet.key, putRet.hash);
    }

    /**
     * 删除图片
     * @param fileName
     */
    public static void remove(String fileName) throws Exception{
        Configuration cfg = new Configuration(Zone.zone0());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        bucketManager.delete(bucket, key);
    }

    /**
     * 批量删除
     * @param fileNames
     * @throws Exception
     */
    public static void removeBatch(String[] fileNames) throws Exception{
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        bucketManager.batch(new BucketManager.Batch().delete(bucket, fileNames));
    }

    /**
     * 批量查询文件是否存在云存储中
     * @param fileNames 文件名称
     * @return 每个文件存在与否详细map
     * @throws Exception
     */
    public static Map<String, Boolean> existsMapBatch(String[] fileNames) throws Exception{
        return existsMapBatch(Arrays.asList(fileNames));
    }

    /**
     * 批量查询文件是否存在云存储中
     * @param fileNameList 文件名称
     * @return 每个文件存在与否详细map
     * @throws Exception
     */
    public static Map<String, Boolean> existsMapBatch(List<String> fileNameList) throws Exception{
        Map<String, Boolean> result = new HashMap<>();
        BatchStatus[] batchStatusList = existsBatch(fileNameList);
        for (int i = 0; i < fileNameList.size(); i++) {
            BatchStatus status = batchStatusList[i];
            String key = fileNameList.get(i);
            result.put(key, status.code == 200);
        }
        return result;
    }

    /**
     * 批量查询文件是否存在云存储中
     * @param fileNameList 文件名称
     * @return 存在存储中的文件名称
     * @throws Exception
     */
    public static List<String> existsListBatch(List<String> fileNameList) throws Exception{
        List<String> result = new ArrayList<>();
        BatchStatus[] batchStatusList = existsBatch(fileNameList);
        for (int i = 0; i < fileNameList.size(); i++) {
            BatchStatus status = batchStatusList[i];
            String key = fileNameList.get(i);
            if(!result.contains(key) && status.code == 200){
                result.add(key);
            }
        }
        return result;

    }

    /**
     * 批量查询文件在七牛云中的状态(是否存在)
     * @param collection 集合
     * @return
     * @throws Exception
     */
    private static BatchStatus[] existsBatch(Collection<?> collection) throws Exception {
        String[] keyList = collection.toArray(new String[collection.size()]);
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        Response response = bucketManager.batch(new BucketManager.Batch().stat(bucket, keyList));
        return response.jsonToObject(BatchStatus[].class);
    }
}
