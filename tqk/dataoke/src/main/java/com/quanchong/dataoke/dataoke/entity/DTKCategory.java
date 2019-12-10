package com.quanchong.dataoke.dataoke.entity;

import lombok.Data;

import java.util.List;

@Data
public class DTKCategory {

    private String cid;
    private String cname;
    private String cpic;
    private List<SubCategory> subcategories;

    @Data
    public static class SubCategory {
        private String subcid;
        private String scname;
        private String scpic;
    }

}
