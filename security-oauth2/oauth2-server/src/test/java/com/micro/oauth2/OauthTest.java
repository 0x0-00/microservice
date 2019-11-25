package com.micro.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/22
 * @Time: 17:36
 * @Version: microservice 1.0
 */
@RunWith(SpringRunner.class)
public class OauthTest {

    @Test
    public void test(){
       //String pwd = new BCryptPasswordEncoder().encode("123");
        String pwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123");
        System.out.println(pwd);
    }
}
