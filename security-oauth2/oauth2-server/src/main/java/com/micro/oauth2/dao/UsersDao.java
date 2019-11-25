package com.micro.oauth2.dao;

import com.micro.oauth2.entity.Permission;
import com.micro.oauth2.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 17:15
 * @Version: microservice 1.0
 */
@Mapper
public interface UsersDao {
    Users getUserByUserName(String loginName);
    List<Permission> getUserPermission(Long userId);
}
