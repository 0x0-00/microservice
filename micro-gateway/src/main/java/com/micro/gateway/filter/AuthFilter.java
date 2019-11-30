package com.micro.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/28
 * @Time: 10:38
 * @Function：将用户信息转发给微服务
 * @Version: microservice 1.0
 */
public class AuthFilter extends ZuulFilter {

    /**
     * 过滤器类型（前置or后置）
     * @pre 前置过滤
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器业务处理
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //获取用户身份
        if(!(authentication instanceof OAuth2Authentication)){
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication(); //取出OAuth2格式的用户身份信息
        //用户名
        String login_name = userAuthentication.getName();
        //用户权限
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(auth -> authorities.add(((GrantedAuthority)auth).getAuthority()));
        //获取其他信息
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String,Object> jsonToken = new HashMap<>(requestParameters);
        jsonToken.put("login_name",login_name);
        jsonToken.put("authorities",authorities);
        ObjectMapper objectMapper = new ObjectMapper();
        //把身份信息以及权限信息加入http的header中,转发给微服务
        try {
            currentContext.addZuulRequestHeader("json_token",objectMapper.writeValueAsString(jsonToken));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
