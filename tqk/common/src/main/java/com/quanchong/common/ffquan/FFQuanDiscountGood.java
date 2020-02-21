package com.quanchong.common.ffquan;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_ffq_discount_good")
public class FFQuanDiscountGood {
    private String id;
    private String cId;
    private String yuanjia;
    private String jiage;
    private String xiaoliang;
    private String quanTime;
    private String quanJine;
    private String huodongType;
    private String pic;
    private String activityEndTime;
    private String dtitle;
    private String isImgCompress;
}
