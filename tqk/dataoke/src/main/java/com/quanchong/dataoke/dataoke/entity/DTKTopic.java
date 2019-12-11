package com.quanchong.dataoke.dataoke.entity;

import lombok.Data;

import java.util.List;

@Data
public class DTKTopic {
    private String topicId;
    private String topicName;
    private String startTime;
    private String endTime;
    private List<String> banner;
}
