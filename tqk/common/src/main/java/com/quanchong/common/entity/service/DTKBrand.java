package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_brand")
public class DTKBrand {
    private String brandId;
    private String brandName;
    private String brandLogo;
    private String brandEnglish;
    private String name;
    private String sellerId;
    private String brandScore;
    private String location;
    private String establishTime;
    private String belong;
    private String position;
    private String consumer;
    private String label;
    private String simpleLabel;
    private String cids;
}
