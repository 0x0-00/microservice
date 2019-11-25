package com.micro.oauth2.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 18:00
 * @Function：资源服务器
 * @Version: microservice 1.0
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "resource1";

    public Oauth2ResourceServiceConfig() {
        super();
    }

    /**
     * @resourceId 资源id（在server中配置的resourceIds）
     * @tokenServices 验证令牌是否合法
     * @stateless
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenServices(tokenService())
                .stateless(true);
    }

    /**
     * 拦截请求校验客户端权限
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 令牌解析服务
     * 远程请求授权服务器校验token
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenService(){
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://127.0.0.1:8081/oauth/check_token");
        service.setClientId("client1");
        service.setClientSecret("secret");
        return service;
    }
}
