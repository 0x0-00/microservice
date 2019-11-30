package com.micro.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 10:17
 * @Function：Token配置类
 * @Version: microservice 1.0
 */
@Configuration
public class TokenConfig {

    private static final String SIGNING_KEY = "oauth-server"; //JWT密钥

    /**
     * JWT令牌存储方案
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * JWT令牌加密算法
     * SIGNING_KEY //对称密钥，资源服务器使用该密钥来验证
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

}
