package com.quanchong.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tqk-web", url = "http://localhost:8085")
@RequestMapping("/tb/ju/tqg")
public interface TbJuTqgItemFeignClient {

    @RequestMapping(value = "/doCollect", method = RequestMethod.POST)
    boolean doCollect(@RequestParam String startTime, @RequestParam String endTime);

    @RequestMapping(value = "/removeAll", method = RequestMethod.POST)
    boolean removeAll();

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    long queryCount();
}
