package com.quanchong.dataoke.top;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.NTbkShop;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.request.TbkSpreadGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import com.taobao.api.response.TbkSpreadGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 淘宝客api包装
 */
@Slf4j
@Component
public class TbkService {

    /**
     * 淘宝客相关配置
     */
    @Autowired
    private TbkConfig tbkConfig;

    private TaobaoClient tbClient = null;

    /**
     * 初始化淘宝客客户端
     */
    @PostConstruct
    public void init(){
        tbClient = new DefaultTaobaoClient(tbkConfig.getServerUrl(),
                tbkConfig.getAppKey(), tbkConfig.getSecret());
    }

    /**
     * 生成淘口令
     * @param url 口令弹框目标页
     * @return
     * @throws Exception
     */
    public String createTPwd(String url) throws Exception{
        return createTPwd("复制淘口令,打开淘宝APP即可查看!", url);
    }


    /**
     * 生成淘口令
     * @param text 口令弹框内容
     * @param url 口令弹框目标页
     * @return
     */
    public String createTPwd(String text, String url) throws Exception{
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText(text);
        req.setUrl(url);
        TbkTpwdCreateResponse resp = tbClient.execute(req);
        return resp.getData().getModel();
    }

    /**
     * 长链接转短链接
     * @param longUrls
     * @return
     * @throws Exception
     */
    public List<String> getShortUrls(String... longUrls) throws Exception {
        TbkSpreadGetRequest req = new TbkSpreadGetRequest();
        List<TbkSpreadGetRequest.TbkSpreadRequest> list = new ArrayList<>();
        Stream.of(longUrls).forEach(url->{
            TbkSpreadGetRequest.TbkSpreadRequest sr = new TbkSpreadGetRequest.TbkSpreadRequest();
            sr.setUrl(url);
            list.add(sr);
        });
        req.setRequests(list);
        TbkSpreadGetResponse resp = tbClient.execute(req);
        List<TbkSpreadGetResponse.TbkSpread> rList = resp.getResults();
        if(null!=rList && !rList.isEmpty()){
            return rList.stream().map(r->r.getContent()).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 获取商品详情
     * @param itemId
     * @return
     * @throws Exception
     */
    public List<TbkItemInfoGetResponse.NTbkItem> getTBKGoodDetail(String itemId) throws Exception {
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids(itemId);
        TbkItemInfoGetResponse resp = tbClient.execute(req);
        if(!ObjectUtils.isEmpty(resp)){
            return resp.getResults();
        }
        return null;
    }
}
