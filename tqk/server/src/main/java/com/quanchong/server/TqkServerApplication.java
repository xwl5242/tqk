package com.quanchong.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * tqk-server
 * eureka 服务
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.quanchong.server.mapper")
@ComponentScan({"com.quanchong.common.config", "com.quanchong.server"})
public class TqkServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TqkServerApplication.class, args);
    }
}
