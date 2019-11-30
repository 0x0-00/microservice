package com.micro.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 10:04
 * @Function：登录相关处理
 * @Version: microservice 1.0
 */
@Controller
public class LoginController {


    /**
     * 自定义登录页面
     */
    @GetMapping("/auth/login")
    public String loginAction(){
        return "/oauth_login";
    }

    /**
     * 获取用户登录成功后的信息
     * @SecurityContextHolder Security上下文，存储着当前认证成功的用户信息
     * @getPrincipal 获取用户身份
     * @return
     */
    @PostMapping("/Home")
    @ResponseBody
    public String skipHome(){
        String name = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(null == principal){
            name = "匿名";
        }
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            name = userDetails.getUsername();
        }else {
            name = principal.toString();
        }

        return name+"登录成功";
    }


    @GetMapping("/admin/adminDetails")
    @ResponseBody
    public Map<String,Object> adminDetails(){
        Map<String,Object> admin = new HashMap<>();
        admin.put("username","管理员");
        admin.put("age",50);
        admin.put("mobile",15638273829L);
        return admin;
    }

    /**
     * @PreAuthorize 执行方法前鉴权
     * @return
     */
    @GetMapping("/user/userDetails")
    @PreAuthorize("hasAuthority('p3')")
    @ResponseBody
    public Map<String,Object> userDetails(){
        Map<String,Object> user = new HashMap<>();
        user.put("username","程序猿");
        user.put("age",20);
        user.put("mobile",15684728372L);
        return user;
    }
}
