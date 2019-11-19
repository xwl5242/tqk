package com.quanchong.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TqkEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TqkEurekaApplication.class, args);
    }
}
