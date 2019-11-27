package com.micro.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 8:39
 * @Function：认证授权服务器 内存方式
 * @Version: microservice 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore; //Token配置策略

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter; //JWT令牌

    @Autowired
    private ClientDetailsService clientDetailsService; //客户端信息

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices; //授权码模式

    @Autowired
    private AuthenticationManager authenticationManager;  //认证管理器

    public AuthorizationServer() {
        super();
    }

    /**
     * 配置客户端信息服务 基于内存
     * @inMemory 存储信息
     * @withClient 客户端标识
     * @secret 客户端密钥
     * @resourceIds 资源列表（指定可以访问那些资源）
     * @authorizedGrantTypes 授权方式
     * @scopes 允许的授权范围 （客户端的权限范围）
     * @autoApprove 是否跳转到授权页面 false跳转 true直接发令牌
     * @redirectUris 验证回调地址
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        /*clients.inMemory()
                .withClient("client1")
                .secret("{noop}secret")
                .resourceIds("resource1")
                .authorizedGrantTypes(new String[]{"authorization_code", "password", "client_credentials", "implicit", "refresh_token"})
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");*/
    }

    /**
     * 配置客户端信息服务 基于数据库
     * @param dataSource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 令牌端点的安全策略（端点：url）
     * @tokenKeyAccess 提供公有密钥的端点 JWT令牌使用
     * @checkTokenAccess 资源访问的令牌解析端点 permitAll公开访问
     * @allowFormAuthenticationForClients 允许表单提交申请令牌
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 令牌访问端点
     * @authenticationManager 认证管理器（）
     * @authorizationCodeServices 授权码存储方式
     * @tokenServices 令牌管理服务（令牌存储方式）
     * @allowedTokenEndpointRequestMethods 允许哪些请求方式获取令牌
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(new HttpMethod[]{HttpMethod.POST,HttpMethod.GET});
    }

    /**
     * 令牌管理服务
     * @DefaultTokenServices 令牌管理服务类
     * @setClientDetailsService 客户端信息服务
     * @setSupportRefreshToken 是否产生刷新令牌
     * @setTokenStore 令牌存储策略
     * @setAccessTokenValiditySeconds 令牌有效时间
     * @setRefreshTokenValiditySeconds 刷新令牌有效期
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);

        //令牌增强 （JWT）
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        service.setAccessTokenValiditySeconds(7200);
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * 设置授权码存储方式
     * @InMemoryAuthorizationCodeServices 内存模式
     * @return
     */
    /*@Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }*/

    /**
     * 授权码存储方式 基于数据库
     * @param dataSource
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
