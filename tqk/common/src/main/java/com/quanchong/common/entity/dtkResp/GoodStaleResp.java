package com.quanchong.common.entity.dtkResp;

import lombok.Data;

import java.util.List;

@Data
public class GoodStaleResp {
    private Long totalNum;
    private String pageId;
    private List<GoodStale> list;

    @Data
    public static class GoodStale{
        private String id;
        private String goodsId;
    }
}
