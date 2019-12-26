package com.quanchong.common.entity.dtkResp;

import lombok.Data;

import java.util.List;

@Data
public class TopicResp {
    private String topicId;
    private String topicName;
    private String startTime;
    private String endTime;
    private List<String> banner;
}
