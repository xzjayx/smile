package com.smile.gateway.Filter;

import com.smile.basic.core.config.common.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头 因为白名单本来就不需要认证所以不需要token头
 * https://blog.csdn.net/kimichen123/article/details/121028212  WebFilter 和GlobalFilter区域
 * AntPathMatcher URL匹配规则
 * https://code84.com/289261.html
 */
@Component
@Slf4j
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                //会进来两次同一次URL匹配成功之后会走两次，暂时不知道为什么
                //log.info(String.format("The URL will be ignored URL:%s",uri.getPath()));
                request = exchange.getRequest().mutate().header("Authorization", "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }

}
