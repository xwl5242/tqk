package com.quanchong.dataoke.miniprogram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "miniprogram")
@Data
public class MiniProgramConfig {
    private String appId;
    private String secret;
}
