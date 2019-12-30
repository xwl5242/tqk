package com.quanchong.common.ffquan;

import lombok.Data;

import java.util.List;

@Data
public class FFQuanDiscountResp {
    private String banner;
    private List<FFQuanDiscountGood> list;
}
