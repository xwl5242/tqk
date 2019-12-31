package com.quanchong.dataoke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableEurekaClient
@MapperScan("com.quanchong.dataoke.mapper")
@ComponentScan({"com.quanchong.common.config", "com.quanchong.dataoke"})
public class TqkDaTaoKeApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TqkDaTaoKeApplication.class, args);
    }
}
