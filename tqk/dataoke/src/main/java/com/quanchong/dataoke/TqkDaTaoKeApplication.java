package com.quanchong.dataoke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class TqkDaTaoKeApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TqkDaTaoKeApplication.class, args);
    }
}
