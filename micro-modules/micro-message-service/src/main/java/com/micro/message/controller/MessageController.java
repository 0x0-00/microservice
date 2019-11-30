package com.micro.message.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/28
 * @Time: 16:52
 * @Version: microservice 1.0
 */
@RestController
public class MessageController {

    @GetMapping("/message/messageDetails")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String messageDetails(){
        String login_name = (String ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return login_name+"访问消息资源";
    }
}
