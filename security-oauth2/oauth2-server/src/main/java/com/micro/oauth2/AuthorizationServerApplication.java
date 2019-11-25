package com.micro.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/20
 * @Time: 16:25
 * @Version: microservice 1.0
 */
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
