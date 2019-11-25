package com.micro.oauth2.entity;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/23
 * @Time: 10:23
 * @Function：角色类
 * @Version: microservice 1.0
 */
@Data
public class Role {
    private Long id;
    private String roleName;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String status;
    private List<Permission> permissions;

}
