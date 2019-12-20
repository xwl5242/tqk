package com.quanchong.dataoke.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_good_coupon")
public class DTKGoodCoupon {
    private String couponClickUrl;
    private String couponStartTime;
    private String couponEndTime;
    private String couponInfo;
    private String itemId;
    private String couponTotalCount;
    private String couponRemainCount;
    private String itemUrl;
    private String tpwd;
    private String maxCommissionRate;
    private String shortUrl;
    private String isExpire;
}
