package com.quanchong.common.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayOutputStream;
import java.net.URL;

@Slf4j
public class ImageUtils {

    /**
     * 按比例压缩
     * @param url
     * @param scale
     * @return
     */
    public static byte[] compressImage(String url, float scale) {
        byte[] smallImage = null;
        try{
            String fileType = url.substring(url.lastIndexOf(".")+1);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Thumbnails.of(new URL(url)).scale(scale).outputFormat(fileType).toOutputStream(out);
            smallImage = out.toByteArray();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return smallImage;
    }

    /**
     * 自定义大压缩
     * @param url
     * @param ppi
     * @return
     */
    public static byte[] compressImage(String url, int ppi) {
        byte[] smallImage = null;
        try{
            String fileType = url.substring(url.lastIndexOf(".")+1);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Thumbnails.of(new URL(url)).size(ppi, ppi).outputFormat(fileType).toOutputStream(out);
            smallImage = out.toByteArray();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return smallImage;
    }

}
