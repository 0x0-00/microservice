package com.micro.oauth2.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 16:48
 * @Function：用户类
 * @Version: microservice 1.0
 */
@Data
public class Users {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
    private List<Role> roles;

}
