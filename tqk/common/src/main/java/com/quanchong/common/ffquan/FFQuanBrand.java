package com.quanchong.common.ffquan;

import lombok.Data;

import java.util.List;

@Data
public class FFQuanBrand {
    private String brandId;
    private String brandName;
    private String brandLogo;
    private String recentSale;
    private String brandDes;
    private String brandWenan;
    private String label;
    private String fansNum;
    private Label brandLabelOne;
    private Label brandLabelTwo;
    private List<FFQuanBrandGood> hotPush;

    @Data
    public static class Label{
        private String val;
    }
}
