package com.quanchong.dataoke.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanchong.common.entity.service.DTKCollect;
import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.common.util.DateUtils;
import com.quanchong.common.util.JwtEnum;
import com.quanchong.common.util.JwtUtils;
import com.quanchong.dataoke.service.sys.DTKCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 大淘客收藏
 */
@RestController
@RequestMapping("/dtk/collect")
public class DTKCollectController {

    @Autowired
    private DTKCollectService dtkCollectService;

    /**
     * 添加收藏
     * @param collectJson
     * @return
     */
    @PostMapping("/add")
    public boolean addCollect(@RequestBody JSONObject collectJson){
        DTKCollect collect = collectJson.getJSONObject("collect").toJavaObject(DTKCollect.class);
        JwtUtils.Token token = collectJson.getJSONObject("token").toJavaObject(JwtUtils.Token.class);
        JwtEnum je = JwtUtils.validateToken(token.getToken());
        if(JwtEnum.TOKEN.getCode().equals(je.getCode())){
            String openId = JwtUtils.parseOpenIdFromTokenSubject(je.getMsg());
            if(!StringUtils.isEmpty(openId)){
                QueryWrapper<DTKCollect> wrapper = new QueryWrapper<>();
                wrapper.eq("open_id", openId);
                wrapper.eq("good_id", collect.getGoodId());
                wrapper.eq("is_del", "0");
                DTKCollect hasCollect = dtkCollectService.getOne(wrapper);
                if(null == hasCollect) {
                    collect.setCollectTime(DateUtils.now());
                    return dtkCollectService.save(collect);
                }else{
                    hasCollect.setCollectTime(DateUtils.now());
                    return dtkCollectService.updateById(hasCollect);
                }
            }
        }
        return false;
    }

    /**
     * 查询收藏列表
     * @param token
     * @return
     */
    @GetMapping("/list")
    public List<DTKGood> list(JwtUtils.Token token){
        JwtEnum je = JwtUtils.validateToken(token.getToken());
        if(JwtEnum.TOKEN.getCode().equals(je.getCode())){
            String openId = JwtUtils.parseOpenIdFromTokenSubject(je.getMsg());
            if(!StringUtils.isEmpty(openId)){
                return dtkCollectService.selectCollectGoods(openId);
            }
        }
        return new ArrayList<>();
    }

    /**
     * 删除收藏
     * @param idAttr
     * @return
     */
    @PostMapping("/remove")
    public boolean remove(@RequestBody Map<String,String> idAttr) {
        List<DTKCollect> collects = new ArrayList<>();
        String ids = idAttr.get("idAttr");
        if(ids.contains(";")){
            ids = ids.substring(0, ids.length()-1);
            for(String id: ids.split(";")){
                DTKCollect collect = new DTKCollect();
                collect.setId(id);
                collect.setIsDel("1");
                collects.add(collect);
            }
        }else{
            DTKCollect collect = new DTKCollect();
            collect.setId(ids);
            collect.setIsDel("1");
            collects.add(collect);
        }
        return dtkCollectService.updateBatchById(collects);
    }
}
