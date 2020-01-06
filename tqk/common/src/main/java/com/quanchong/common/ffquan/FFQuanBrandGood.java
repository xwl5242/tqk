package com.quanchong.common.ffquan;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.quanchong.common.base.BaseGood;
import lombok.Data;

import java.util.List;

@Data
@TableName("dtk_ffq_brand_good")
public class FFQuanBrandGood extends BaseGood {
    private String id;
    private String brandId;
    private String labelTwo;
    private String huodongType; // 1:无;3:聚划算
    private String pic;
    private String yuanjia;
    private String jiage;
    private String discount;
    private String dtitle;
    private String xiaoliang;
    private String sellDear;
    private String goodsLabels;
}
