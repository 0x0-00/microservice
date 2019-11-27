package com.micro.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/25
 * @Time: 18:39
 * @Version: microservice 1.0
 */
@RestController
public class ResourceController {

    @GetMapping("/user/resource")
    @PreAuthorize("hasAuthority('p2')")
    public String userResource(){
        return "获取资源成功";
    }
}
