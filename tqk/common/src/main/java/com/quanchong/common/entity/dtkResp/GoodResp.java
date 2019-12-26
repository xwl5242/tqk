package com.quanchong.common.entity.dtkResp;

import com.quanchong.common.entity.service.DTKGood;
import lombok.Data;

import java.util.List;

@Data
public class GoodResp {
    private Long totalNum;
    private String pageId;
    private List<DTKGood> list;
}
