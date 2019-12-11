package com.quanchong.dataoke.dataoke.entity;

import com.quanchong.dataoke.entity.DTKGood;
import lombok.Data;

import java.util.List;

@Data
public class DTKGoodResp {
    private Long totalNum;
    private String pageId;
    private List<DTKGood> list;
}
