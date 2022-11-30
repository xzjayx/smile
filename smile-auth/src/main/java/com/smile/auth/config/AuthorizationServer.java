package com.smile.auth.config;

import com.smile.auth.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.io.InputStream;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/*
*  oauth2.0 最核心的就是认证服务器Auth 给用户颁发授权，能过让其访问到别的资源服务器等gateway
* 该类认证服务核心就两点：客户端信息配置和access_token生成配置
* Oauth2ServerConfig
AuthorizationServer主要配置有三大类，安全配置、端点配置、客户端配置。
安全配置主要针对授权服务器端点的访问策略、认证策略、加密方式等进行配置。
端点配置主要配置授权服务器的token存储方式、token转换、端点增强、端点自定义、token授权、token生成等进行配置。
客户端配置主要配置接入的客户端相关信息，如授权类型、授权范围、秘钥等内容。 ClientDetailsServiceConfigurer
*
* */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private final UserServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    private final PasswordEncoder passwordEncoder;


    //clientId：（必填）客户端ID。 --> client_id
    //secret: (可信客户端需要）客户机密码（如果有）。没有可不填 ---> client_secret
    //scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
    //authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。 ---> 四种常用授权方式
    //authorities授予客户的授权机构（普通的Spring Security权威机构）。
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //暂时内存中，后面放入数据库中
        clients.inMemory()
                .withClient("client-app")
                .secret(passwordEncoder.encode("123456"))
                .scopes("all")
                //密码模式 oauth2.0有四种模式，常用的为授权码模式（第三方登录） 密码模式
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(86400)
                .and()
                .withClient("admin")//配置client_id
                .secret(passwordEncoder.encode("123456"))//配置client_secret
                .accessTokenValiditySeconds(3600)//配置访问token的有效期
                .refreshTokenValiditySeconds(864000)//配置刷新token的有效期
                .redirectUris("http://www.baidu.com")//配置redirect_uri，用于授权成功后跳转
                .scopes("all")//配置申请的权限范围
                .authorizedGrantTypes("authorization_code","refresh_token");//配置grant_type，表示授权类型
//        JdbcClientDetailsService  jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//        clients.withClientDetails(jdbcClientDetailsService);


    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);
        endpoints
                //密码模式需要一个授权管理器必须要配置不然认证会报错
                .authenticationManager(authenticationManager)
                //授权模式源码会自动配置默认内存管理器来管理code 可以自定义code 换成不是一次性失效的，因为在内存中用code一次即失效但是从业务角度来说没有必要因为有密码模式
                //.authorizationCodeServices()
                //配置加载用户信息的服务 spring security 管理的User
                .userDetailsService(userDetailsService)
                //access_token的转换器因为采用了jwt则默认用JwtAccessTokenConverter
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    /** 允许客户端发起表单请求*/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //开启/oauth/token_key验证端口认证权限访问
                //.tokenKeyAccess("isAuthenticated()")    不会用到的 分布式 + 非jwt 就需要开启 /oauth/check_token
                // 是的，它资源服务器也是调用的授权服务的/oauth/check_token端点，校验的token
                //开启/oauth/check_token验证端口认证权限访问
                //.checkTokenAccess("isAuthenticated()")

                .allowFormAuthenticationForClients();
    }

    /*
     * 在 JWT 编码的令牌值和 OAuth 身份验证信息（双向）之间转换的助手。授予令牌时还充当TokenEnhancer
     * JwtAccessTokenConverter是生成token的转换器，可以实现指定token的生成方式(JWT)和对JWT进行签名。
     * */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     *  在 jwt中 头部 身体 尾部签名，是需要授权密钥签名算法，要么是对称加密HMAC，可能是非对称加密RSA需要公钥和私钥，
     *  这里就是RSA算法生成密钥对  要记住密钥口令
     *  keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
     *  JKS 密钥库使用专用格式。建议使用
     *  "keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
     * */
    @SneakyThrows
    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        ClassPathResource pathResource = new ClassPathResource("jwt.jks");
        InputStream inputStream = pathResource.getInputStream();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new InputStreamResource(inputStream), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }
}
