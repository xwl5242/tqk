package com.quanchong.dataoke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.quanchong.dataoke.mapper")
public class TqkDaTaoKeApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TqkDaTaoKeApplication.class, args);
    }
}
