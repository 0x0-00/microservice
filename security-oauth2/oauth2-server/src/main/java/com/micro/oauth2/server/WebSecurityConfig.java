package com.micro.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 9:08
 * @Function：用户信息配置类 基于内存
 * @Version: microservice 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    /**
     * 自定义用户信息
     * @param auth
     * @throws Exception
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }*/

    /**
     * 认证管理器（资源所有者以密码方式授权）
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义用户信息
     * @manager 内存类
     * @authorities 用户权限
     * @return
     */
    /*@Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build()); //创建基于内存的临时用户
        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
        return manager;
    }*/

    /**
     * 密码编码器
     * @createDelegatingPasswordEncoder 根据数据库里密码的类型自动匹配验证规则
     * @Function 定义比对用户密码的方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 安全拦截机制
     * @Function 拦截用户请求，验证用户访问资源需要什么权限
     * @authorizeRequests 认证授权请求
     * @hasAuthority 访问资源需要具有哪些权限（单个权限）
     * @hasAnyAuthority 访问资源需要具有哪些权限（多个权限）
     * @authenticated 请求必须认证通过
     * @anyRequest 其他请求
     * @permitAll 无条件允许访问 会给用户适配一个Token，方便处理
     * @formLogin 允许表单登录
     * @loginProcessingUrl 表单提交的路径
     * @loginPage 没有权限访问页面（认证失败）后回调的地址
     * @successForwardUrl 自定义登录成功的页面地址 转发
     * @sessionManagement security的会话管理
     * @logout 退出登录
     * @logoutUrl 自定义退出登录地址
     * @logoutSuccessUrl 退出成功后的回调地址
     * @throws Exception
     * 注意：.anyRequest().permitAll() 不能放在.antMatchers()拦截路径的前面，不然antMatchers会无效
     *       security的拦截授权是从上到下执行！ 具体的拦截规则放在最前面！
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/adminDetails").hasAuthority("p1")
                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin();
                /*.loginProcessingUrl("/user/login")
                .loginPage("/login")
                .successForwardUrl("/Home")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .logout()
                .logoutUrl("/exit")
                .logoutSuccessUrl("/login?exit");*/
    }
}
