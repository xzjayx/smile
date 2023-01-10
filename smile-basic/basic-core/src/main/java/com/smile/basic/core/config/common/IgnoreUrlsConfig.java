package com.smile.basic.core.config.common;

import com.smile.basic.core.constant.EnvConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * gateway网关白名单配置默认启动,配置文件ignore url，例如 /smile-admin/actuator/** url请求中带有route的匹配谓词，则走服务
 * /actuator/** 匹配不上则直接走当前网关服务的url，在过滤的url中找不到对应的动态route，注意：网关白名单如需生效一定要以网关发起
 * 例外：url为spring匹配路径在admin服务中，由于admin自定义的过滤器所以，具体实现与否参见
 * @see com.smile.sys.filter.TokenFilter 暂时废弃
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Configuration
@ConfigurationProperties(prefix = EnvConstants.GATEWAY_IGNORE_URL_PREFIX)
public class IgnoreUrlsConfig {


    private List<String> urls;



    /*
      现在有两个请求比如A请求 http://localhost:9527/auth/oauth/token，和B请求 http://localhost:9527/actuator/bean
    这两个URL只有中间一个auth区别，由于gateway配置文件对auth做了动态api匹配 routes#配置路由路径 找到对应的oauth服务名
    过滤之后A请求会变成--->断言 http://smile-auth/auth/oauth/token (http://192.168.5.1:8100/auth/actuator/bean) ->截取
    http://92.168.5.1:8100/oauth/token   而B请求因为没有url谓词匹配还是原来的请求  见spring gateway 路由配置

    * 这个白名单配置作用有个细节：

         举例子如果配置 /actuator/**  配置后者 /api/actuator/**
    *    白名单处理逻辑：首先对进入网关的请求的URL进行白名单配置过滤URL匹配，如果请求配对成功之后，再会走gateway route的路由配置（见上）
               路由转发完毕之后，就会进入目标服务，再看目标服务是否配置过spring security URL过滤之类的一般来说除了授权服务都不会配置
               一般的资源服务就会直接访问，而配置过spring security 安全过滤的服务则需要在进行URL匹配，成功即可访问。

     */


}
