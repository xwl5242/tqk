package com.quanchong.server.feign;

import com.quanchong.common.entity.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tqk-web", url = "http://localhost:8085")
@RequestMapping("//tb/goods")
public interface TbGoodsFeignClient {

    @RequestMapping(value = "/queryByMaterialId", method = RequestMethod.GET)
    List<TbItem> queryByMaterialId(@RequestParam("materialId") String materialId,
                                   @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize);
}
