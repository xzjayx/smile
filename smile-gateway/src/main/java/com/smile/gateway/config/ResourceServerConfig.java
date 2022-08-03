package com.smile.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.smile.basic.core.config.common.IgnoreUrlsConfig;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.gateway.Filter.IgnoreUrlsRemoveJwtFilter;
import com.smile.gateway.authorization.AuthorizationManager;
import com.smile.gateway.handler.RestAuthenticationEntryPointHandler;
import com.smile.gateway.handler.RestfulAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置
 * 对网关服务进行配置安全配置，由于Gateway使用的是WebFlux，所以需要使用@EnableWebFluxSecurity注解开启；
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPointHandler restAuthenticationEntryPoint;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //解决当gateway充当OAuth2ResourceServer的时候搭配，会出现hasRole配置无效的问题。
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        //对白名单路径，直接移除JWT请求头 在特定位置之前添加一个WebFilter
        // SecurityWebFiltersOrder.AUTHENTICATION 认证授权位置之前添加一个白名单移除JWT的过滤器,必须要过滤token，如
        // 果不过滤会发现后面白名单配置还是无法过滤成功 https://www.cnblogs.com/cndarren/p/15822130.html
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                 //白名单配置
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class)).permitAll()
                 //映射任何请求--->鉴权管理器配置
                .anyExchange().access(authorizationManager)
                 //配置异常处理
                .and().exceptionHandling()
                 //处理未授权无访问权限
                .accessDeniedHandler(restfulAccessDeniedHandler)
                 //处理未认证没有登录
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().csrf().disable();
        return http.build();
    }


    /**
     * 当gateway充当OAuth2ResourceServer的时候，会出现hasRole配置无效的问题。
     * 原因来自于ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec中默认的ReactiveAuthenticationManager没有
     * 将jwt中authorities 的负载部分当做Authentication的权限。
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义ReactiveAuthenticationManager权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     *  https://blog.csdn.net/qq_24230139/article/details/105091273
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(GlobalConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(GlobalConstants.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
