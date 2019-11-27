package com.micro.oauth2.service;

import com.micro.oauth2.dao.UsersDao;
import com.micro.oauth2.entity.Permission;
import com.micro.oauth2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 11:36
 * @Function：用户信息配置类 基于数据库
 * @Version: microservice 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        //UserDetails userDetails = User.withUsername("dai").password("123").authorities("p1").build();
        Users user = usersDao.getUserByUserName(loginName);
        if(null == user){
            return null;
        }
        List<Permission> permissions = usersDao.getUserPermission(user.getId());
        List<String> permList = new ArrayList<>();
        permissions.forEach( permission -> permList.add(permission.getCode()));
        String[] perms = permList.toArray(new String[permList.size()]);
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(perms).build();
        return userDetails;
    }
}
