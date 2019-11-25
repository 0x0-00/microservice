package com.micro.oauth2.entity;

import lombok.Data;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/23
 * @Time: 10:23
 * @Function：权限类
 * @Version: microservice 1.0
 */
@Data
public class Permission {
    private Long id;
    private String code;
    private String description;
    private String url;
}
