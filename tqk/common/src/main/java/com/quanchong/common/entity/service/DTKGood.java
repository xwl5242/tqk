package com.quanchong.common.entity.service;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_good")
public class DTKGood {
    private String id;
    private String goodsId;
    private String itemLink;
    private String title;
    private String dtitle;
    @JSONField(name = "desc")
    private String description;
    @TableField(exist = false)
    private String ddqDesc;
    private String cid;
    private String subcid;
    private String tbcid;
    private String mainPic;
    private String marketingMainPic;
    private String originalPrice;
    private String actualPrice;
    private String discounts;
    private String commissionType;
    private String commissionRate;
    private String couponLink;
    private String couponTotalNum;
    private String couponReceiveNum;
    private String couponEndTime;
    private String couponStartTime;
    private String couponPrice;
    private String couponConditions;
    private String monthSales;
    private String twoHoursSales;
    private String dailySales;
    private String brand;
    private String brandId;
    private String brandName;
    private String createTime;
    private String tchaoshi;
    private String activityType;
    private String activityStartTime;
    private String activityEndTime;
    private String shopType;
    private String haitao;
    private String goldSellers;
    private String sellerId;
    private String shopName;
    private String shopLevel;
    private String descScore;
    private String dsrScore;
    private String dsrPercent;
    private String shipScore;
    private String shipPercent;
    private String serviceScore;
    private String servicePercent;
    private String hotPush;
    private String teamName;
    private String quanMLink;
    private String hzQuanOver;
    private String yunfeixian;
    private String estimateAmount;
    private String nine;
    private String nineCid;
    private String rank;
    private String ranking;
    private String rankType;
    private String newRankingGoods;
    private String isExpire;
    private String isImgCompress;
    @TableField(exist = false)
    private String detailPics;
    @TableField(exist = false)
    private String imgs;
    @TableField(exist = false)
    private String reimgs;
    @TableField(exist = false)
    private String shopLogo;
    @TableField(exist = false)
    private String collectId;
}
