package com.micro.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 10:17
 * @Function：Token配置类
 * @Version: microservice 1.0
 */
@Configuration
public class TokenConfig {

    /**
     * 基于内存存储方式
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

}
