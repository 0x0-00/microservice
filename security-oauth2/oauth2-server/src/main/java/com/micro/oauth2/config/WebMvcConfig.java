package com.micro.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/21
 * @Time: 19:42
 * @Function：页面配置类
 * @Version: microservice 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * @addViewController 拦截用户的跳转请求
     * @setViewName 需要跳转到的页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("redirect:/login");  //security默认提供的页面
        registry.addViewController("/login").setViewName("oauth_login"); //自定义页面
    }

}
