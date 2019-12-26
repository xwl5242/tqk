package com.quanchong.common.entity.dtkResp;

import lombok.Data;

import java.util.List;

@Data
public class SuperCategoryResp {

    private String cid;
    private String cname;
    private String cpic;
    private List<SubCategory> subcategories;

    @Data
    public static class SubCategory {
        private String subcid;
        private String subcname;
        private String scpic;
    }

}
