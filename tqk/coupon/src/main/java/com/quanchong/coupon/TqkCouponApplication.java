package com.quanchong.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@MapperScan("com.quanchong.coupon.mapper")
@ComponentScan({"com.quanchong.common.config", "com.quanchong.coupon"})
public class TqkCouponApplication
{
    public static void main( String[] args )
    {

        SpringApplication.run(TqkCouponApplication.class, args);
    }
}
