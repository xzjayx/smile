package com.smile.basic.core.config.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/*

openFeign的日志级别如下：

NONE：默认的，不显示任何日志;
BASIC：仅记录请求方法、URL、响应状态码及执行时间;
HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息;
FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。
对于ConditionalOnProperty不熟悉的可以看 https://blog.csdn.net/xzjayx/article/details/124419978
额外：需要在开启监听的模块中配置 com.smile.ability.openfeign.customer 是openFeign接口所在的包名，
     当然也可以配置一个特定的openFeign接口
logging:
  level:
    com.smile.api.admin.feign: debug
*/

@Configuration
@ConditionalOnProperty(prefix = "feign.logger",name = "enable",havingValue = "true")
public class OpenFeignLoggerLevel {

    @Value("${feign.logger.level}")
    private String level;

    @Bean
    Logger.Level feignLoggerLevel(){
        List<Logger.Level> collect = Arrays.stream(Logger.Level.values()).filter(x -> x.name()
                .equals(level)).collect(Collectors.toList());
        return collect.get(0);
    }
}
