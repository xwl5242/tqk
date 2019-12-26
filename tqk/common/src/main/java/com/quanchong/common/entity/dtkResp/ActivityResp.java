package com.quanchong.common.entity.dtkResp;

import lombok.Data;

@Data
public class ActivityResp {

    private String activityId;
    private String activityName;
    private String startTime;
    private String endTime;
    private String goodsLabel;
    private String detailLabel;
    private String goodsType;
}
