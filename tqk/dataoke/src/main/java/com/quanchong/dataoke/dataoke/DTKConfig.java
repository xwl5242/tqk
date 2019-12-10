package com.quanchong.dataoke.dataoke;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dataoke")
@Data
public class DTKConfig {
    private String appKey;
    private String appSecret;
    private String pid;
    private String version;
}
