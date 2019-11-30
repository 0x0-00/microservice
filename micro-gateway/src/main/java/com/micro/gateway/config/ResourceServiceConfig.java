package com.micro.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 18:00
 * @Function：统一鉴权
 * @Version: microservice 1.0
 */
@Configuration
public class ResourceServiceConfig {

    public static final String RESOURCE_ID = "resource";

    @Autowired
    private TokenStore tokenStore;

    /**
     * 认证服务资源
     */
    @Configuration
    @EnableResourceServer
    public class AuthenticationServerConfig extends ResourceServerConfigurerAdapter{

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/authorization/**").permitAll();
        }
    }

    /**
     * 消息服务资源
     */
    @Configuration
    @EnableResourceServer
    public class MessageServiceConfig extends ResourceServerConfigurerAdapter{

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/message/**").access("#oauth2.hasScope('reader')");
        }
    }
}
