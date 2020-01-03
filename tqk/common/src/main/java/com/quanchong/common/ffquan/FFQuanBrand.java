package com.quanchong.common.ffquan;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("dtk_ffq_brand")
public class FFQuanBrand {
    private String brandId;
    private String brandName;
    private String brandLogo;
    private String backgroundImg;
    private String longProfit;
    private String recentSale;
    private String brandDes;
    private String brandWenan;
    private String label;
    private String fansNum;
    private String brandLabelOne;
    private String brandLabelTwo;
    private String brandType;
    @TableField(exist = false)
    private List<FFQuanBrandGood> hotPush;
}
