package com.smile.sys.filter;

import cn.hutool.core.collection.CollUtil;
import com.smile.basic.core.config.common.IgnoreUrlsConfig;
import com.smile.basic.core.config.holder.LoginUserHolder;
import com.smile.basic.core.constant.EnvConstants;
import com.smile.basic.core.util.CoreUtil;
import com.smile.basic.core.util.ResponseUtil;
import com.smile.basic.core.util.TokenUtil;
import com.smile.sys.config.SysIgnoreUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *   过滤配置的白名单，如果属性白名单内部，则不判断token请求头
 *  String ant 匹配  白名单 URL: 属于里面的直接走，不属于的在经过Token过滤头
 *  假设默认情况下，白名单配置过并且URL有匹配，则直接过滤不会校验Token，如果不满足则在判断是否有token
 *  gateway的网关的白名单过滤失效和admin服务白名单服务过滤 ordered导致该过滤器会先执行
 *  https://www.csdn.net/tags/MtTaYg5sMzQ4NjItYmxvZwO0O0OO0O0O.html
 *  http://t.zoukankan.com/java-spring-p-12742984.html
 * */
@Slf4j
@Component
@WebFilter(urlPatterns = EnvConstants.URL_PERMIT_ALL, filterName = "urlTokenFilter")
public class UrlTokenFilter implements Filter, Ordered {

    @Autowired
    private IgnoreUrlsConfig gatewayIgnoreUrls;

    @Autowired
    private SysIgnoreUrls adminIgnoreUrls;

    @Autowired
    private Environment environment;

    @Autowired
    private LoginUserHolder loginUserHolder;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        log.info(String.format("The request has been entered filter url:%s ,RemoteAddr:%s ,RemotePort:%s",
                req.getRequestURI(),req.getRemoteAddr(),req.getRemotePort()));
        String application = EnvConstants.URL_PREFIX + environment.getProperty(EnvConstants.SPRING_APPLICATION_NAME);
        List<String> permit;
        switch (adminIgnoreUrls.getCheck()){
            case EnvConstants.CHECK_DEFAULT:
                permit = CoreUtil.UrlUnionDistinct(application, gatewayIgnoreUrls.getUrls(), adminIgnoreUrls.getUrls());
                break;
            case EnvConstants.CHECK_TOW:
                permit = adminIgnoreUrls.getUrls();
                break;
            case EnvConstants.CHECK_THREE:
                permit = gatewayIgnoreUrls.getUrls().stream().filter(x-> x.contains(application))
                        .map(v->v.substring(application.length())).collect(Collectors.toList());
                break;
            case EnvConstants.CHECK_FOUR:
                permit = CoreUtil.UrlMinus(application, gatewayIgnoreUrls.getUrls(), adminIgnoreUrls.getUrls());
                break;
            default:
                permit = null;
                log.info("Not right case");
        }
        boolean flag = !CollUtil.isEmpty(permit) && permit.stream().anyMatch(x ->
                new AntPathMatcher().match(x, req.getRequestURI()));
        if(flag || TokenUtil.isUserExist(loginUserHolder.getCurrentUser())){
            chain.doFilter(request,response);
        }else{
            ResponseUtil.OutUnauthorized(resp);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
