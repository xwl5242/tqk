package com.quanchong.coupon.top;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 淘宝客配置
 */
@Configuration
@ConfigurationProperties(prefix = "tbk")
@Data
public class TbkConfig {

    private String serverUrl; // 请求地址
    private String appKey; // app_key
    private String secret; // secret
    private String adZoneId; // 广告位id
    private String pid; // 媒体id

}
