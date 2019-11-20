package com.quanchong.server.feign;

import com.quanchong.common.entity.TbGood;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tqk-web", url = "http://localhost:8085")
@RequestMapping("/tb/goods")
public interface TbGoodFeignClient {

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    long queryCount();

    @RequestMapping(value = "/removeAll", method = RequestMethod.POST)
    boolean removeAll();

    @RequestMapping(value = "/saveBatch", method = RequestMethod.POST)
    boolean saveBatch(@RequestBody List<TbGood> tbGoodList);

    @RequestMapping(value = "/material/selection", method = RequestMethod.GET)
    List<TbGood> materialSelection(@RequestParam("materialId") String materialId,
                                   @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize);

}
