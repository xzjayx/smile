package com.smile.auth.controller;


import com.smile.auth.domain.Oauth2TokenDto;
import com.smile.basic.core.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    /**
     * 默认是spring security oauth2包里面暴露出来的请求，一般来说会有6个默认请求，这6个在
     * /oauth/authorize ：授权端点。
     * /oauth/token ：令牌端点。
     * /oauth/confirm_access ：用户确认授权提交端点。
     * /oauth/error ：授权服务错误信息端点。
     * /oauth/check_token ：用于资源服务访问的令牌解析端点。
     * /oauth/token_key ：提供公有密匙的端点，如果你使用JWT令牌的话。
     *
     * 观察我们的每一种授权模式的请求地址。我们不难发现，请求地址就是如下两个。
     * 授权码模式，我们请求了如下地址 四种模式: "authorization_code","implicit","password","client_credentials"
     * http://127.0.0.1:8080/oauth/authorize
     * http://127.0.0.1:8080/oauth/token
     * 简化模式
     * http://127.0.0.1:8080/oauth/authorize
     * 客户端模式
     * http://127.0.0.1:8080/oauth/token
     * 密码模式
     * http://127.0.0.1:8080/oauth/token
     *
     *
     * */
    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证  密码模式获取JWT令牌
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();

        return Result.success(oauth2TokenDto);
    }
}
