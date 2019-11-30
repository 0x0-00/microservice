package com.micro.oauth2.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: daiguoqing
 * @Date: 2019/11/29
 * @Time: 10:12
 * @Function：自定义授权
 * @Version: microservice 1.0
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class AuthorizationController {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String,Object> model, HttpServletRequest request){
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.addObject("clientId",authorizationRequest.getClientId());
        view.addObject("scopes",authorizationRequest.getScope());
        view.setViewName("/oauth_authorization");
        return view;
    }
}
