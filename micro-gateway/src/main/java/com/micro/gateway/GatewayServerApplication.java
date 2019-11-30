package com.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/28
 * @Time: 8:25
 * @Version: microservice 1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayServerApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
