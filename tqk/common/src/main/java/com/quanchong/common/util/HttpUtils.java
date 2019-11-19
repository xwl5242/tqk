package com.quanchong.common.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

public class HttpUtils {

    public static String sendGet(String url) throws Exception{
        if(!StringUtils.isEmpty(url)){
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse resp = client.execute(get);
            if(resp.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(resp.getEntity());
            }
        }
        return null;
    }

}
