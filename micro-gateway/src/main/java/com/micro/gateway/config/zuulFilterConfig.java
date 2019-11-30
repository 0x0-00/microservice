package com.micro.gateway.config;

import com.micro.gateway.filter.AuthFilter;
import com.netflix.zuul.ZuulFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/28
 * @Time: 14:32
 * @Version: microservice 1.0
 */
@Configuration
public class zuulFilterConfig {

    /**
     * 将自定义网关过滤器交由spring管理
     * @return
     */
    @Bean
    public ZuulFilter preFilter(){
        return new AuthFilter();
    }

   /* @Bean
    public FilterRegistrationBean corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setMaxAge(18000L);
        source.registerCorsConfiguration("/**",configuration);
        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>(corsFilter);
        corsFilterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsFilterFilterRegistrationBean;

    }*/
}
