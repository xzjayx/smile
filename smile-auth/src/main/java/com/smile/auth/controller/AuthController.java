//package com.smile.auth.controller;
//
//
//import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
//import com.smile.auth.domain.Oauth2TokenDto;
//import com.smile.basic.core.base.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//import java.util.Map;
//
//
///*
//
//自定义Oauth2获取令牌接口仅仅封装返回参数
//Created by xiezhi on 2020/7/17.
//
//*/
//
//
///**
// * @author xiezhi
// */
//@Slf4j
//@RestController
//@Api(tags = "授权模块密码模式")
//public class AuthController {
//
//
///*
//默认是spring security oauth2包里面暴露出来的请求，一般来说会有6个默认请求，这6个在
//    /oauth/authorize ：授权端点。
//    /oauth/token ：令牌端点。 org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
//    /oauth/confirm_access ：用户确认授权提交端点。
//    /oauth/error ：授权服务错误信息端点。
//    /oauth/check_token ：用于资源服务访问的令牌解析端点。
//    /oauth/token_key ：提供公有密匙的端点，如果你使用JWT令牌的话。
//
//    观察我们的每一种授权模式的请求地址。我们不难发现，请求地址就是如下两个。
//    授权码模式，我们请求了如下地址 四种模式: "authorization_code","implicit","password","client_credentials"
//    http://127.0.0.1:8080/oauth/authorize
//    http://127.0.0.1:8080/oauth/token
//    简化模式
//    http://127.0.0.1:8080/oauth/authorize
//    客户端模式
//    http://127.0.0.1:8080/oauth/token
//    密码模式
//    http://127.0.0.1:8080/oauth/token
//*/
//
//
//
//
//
//
//    @Autowired
//    private TokenEndpoint tokenEndpoint;
//
//
///*
//        Oauth2登录认证  密码模式获取JWT令牌 这里仅仅是封装返回结果而已可有可无。原结果如下
//      {
//          "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJleHAiOjE2NzUwNzI4MzksInVzZXJfbmFtZSI6InpoYW5nc2FuIiwianRpIjoiNWQ2Yzc1NjYtMTRiYy00ZGVlLWE0MWQtNGEyN2NmMDI2OWRhIiwiY2xpZW50X2lkIjoiY2xpZW50LWFwcCIsInNjb3BlIjpbImFsbCJdfQ.RHsGr6eDqqUu2RsrprZqt446TuqPouO_UB0gGu-kmI5n6brW39yKtREHe-Cbjl04vYnZT0-9wmG1z3eHC4ZAz4FPBPVS6nDP81O70v42npypPLsl-uFxpWiHaiaiZWZ13qWeGi2qqVoPjCHZN1JiwbSQoNUX_bLX4LCEBcsxWy2mmHHjqh4uvte55W2YNQj-971hCzXtlCNdO6PWe4o9wvGqg3k-8v-SD9_vmW1e_d-iWmTlixe1cogfLoT1syk2vctWnsxwqsnm5Ce7OB0nQmHRoxq0yy6-8_BzhDg6FssiUhwHPf15Cczdqo7gZ9on1wahBAQbJPDKEjN6vb2z4w",
//          "token_type": "bearer",
//          "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ6aGFuZ3NhbiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI1ZDZjNzU2Ni0xNGJjLTRkZWUtYTQxZC00YTI3Y2YwMjY5ZGEiLCJpZCI6IjEiLCJleHAiOjE2NzUxNTU2MzksImp0aSI6IjM5ZTMwZDc0LTQ4MjItNGEwMC1hNTVhLWY0NDFiODc5N2M3OSIsImNsaWVudF9pZCI6ImNsaWVudC1hcHAifQ.XUFki3IPRUH4bGdHQI2R5SyMzNZg3wMFM05TN6SbKJV6CXHR09AHSzGRYZXysAsJrzzsyLmAwiFDT-aciyzE_a_nHENCZbk8t9NhWRCWjiTnxzfvNox7ZBcqAPgibKIsxx9QB_qFCxuGOYUnPo_fRSHCU2Xh7hr3VFVp84xdqnUltYk1Pyugul74jmE47wqLUdyV0-o_CLn04qHCnQXjuZlpwuIXwZ9BDuhlVmIok0rhEUJbWw2ZfVcxF153c0V3iJciISzSyWCP7DUHvRo_ynuOXWRgtBHvrbIWZb9mHDfBTgKClPtS5bJnjfc3VGPfd-MqcSNGItv6L4FgNJg9pA",
//          "expires_in": 3599,
//          "scope": "all",
//          "id": "1",
//          "jti": "5d6c7566-14bc-4dee-a41d-4a27cf0269da"
//      }
//*/
//
//    @RequestMapping(value = "/oauth/password", method = RequestMethod.POST)
//    @ApiOperationSupport(ignoreParameters={"parameters","PostAccessToken"})
//    @ApiOperation(value = "密码模式登录",httpMethod = "POST")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "grant_type", value = "授权模式",required = true,
//                defaultValue = "password", dataType = "String", paramType = "query"),
//        @ApiImplicitParam(name = "client_id", value = "客户端ID",required = true,
//                defaultValue = "client-app", dataTypeClass = String.class,paramType = "query"),
//        @ApiImplicitParam(name = "client_secret", value = "客户端密码",required = true,
//                defaultValue = "123456", dataTypeClass = String.class,paramType = "query"),
//        @ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "zhangsan", dataTypeClass = String.class,paramType = "query"),
//        @ApiImplicitParam(name = "password", value = "密码", required = true, defaultValue = "123456", dataTypeClass = String.class,paramType = "query")
//    })
//    public Result<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        //UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken();
//
//        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
//        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
//                .token(oAuth2AccessToken.getValue())
//                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
//                .expiresIn(oAuth2AccessToken.getExpiresIn())
//                .tokenHead("Bearer ").build();
//
//        return Result.success(oauth2TokenDto);
//    }
//}

/**
 *
 * 由于和knife4j注入有问题，暂时关闭，直接用TokenEndpoint里面的接口去执行
 * */
