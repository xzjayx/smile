package com.smile.basic.core.config.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.smile.basic.core.dto.sys.UserDTO;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息  com.gateway.security.Filter.AuthGlobalFilter 解析jwt 之后插入的请求头可以为user
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(Convert.toStr(userJsonObject.get("id")));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        System.out.println(userDTO.toString());
        return userDTO;
    }

  /**  @Override
    public int getOrder() {
        return -1;
    }*/
}
