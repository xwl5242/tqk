package com.quanchong.server.feign;

import com.quanchong.common.entity.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "tqk-web", url = "http://localhost:8085")
@RequestMapping("/tb/item")
public interface TbItemFeignClient {

    @RequestMapping(value = "/saveBatch", method = RequestMethod.POST)
    boolean saveBatch(@RequestBody List<TbItem> tbItemList);

    @RequestMapping(value = "/removeAll", method = RequestMethod.POST)
    boolean removeAll();

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    long queryCount();

}
