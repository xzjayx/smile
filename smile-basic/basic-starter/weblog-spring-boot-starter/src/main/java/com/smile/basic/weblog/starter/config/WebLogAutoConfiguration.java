package com.smile.basic.weblog.starter.config;

import com.smile.basic.weblog.starter.aspect.WebLogAspect;
import com.smile.basic.weblog.starter.properties.WebLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 * 1 @Configuration 表示类为配置类
 * 2 @EnableConfigurationProperties 注解的作用是:让使用了 @ConfigurationProperties 注解的类生效,
 *   并且将该类注入到 IOC 容器中,交由 IOC 容器进行管理；如果不这么用，那么用到了@ConfigurationProperties
 *   注解的类上面需要加 @Configuration 或者 @Component 注解才可以生效。
 *   https://www.cnblogs.com/xiaomaomao/p/13934688.html
 * 3 @ConditionalOnProperty(prefix = "zzcloud.weblog",value = "enabled", matchIfMissing = true)：
 *   matchIfMissing属性：默认情况下matchIfMissing为false，也就是说如果未进行属性配置，则自动配置不生效。
 *   如果matchIfMissing为true，则表示如果没有对应的属性配置，则自动配置默认生效
 *   https://blog.csdn.net/xzjayx/article/details/124419978
 * 4 @ConditionalOnMissingBean 在@bean定义上，它的作用就是在容器加载它作用的bean时，检查容器中是否存在目标类型
 *  （ConditionalOnMissingBean注解的value值）的bean了，如果存在这跳过原始bean的BeanDefinition加载动作。
 *   不存在就加载
 *
 */
@Configuration
@EnableConfigurationProperties({WebLogProperties.class})
@ConditionalOnProperty(prefix = "smile.weblog",value = "enabled", matchIfMissing = true)
public class WebLogAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public WebLogAspect webLogAspect(){
        return new WebLogAspect();
    }


}
