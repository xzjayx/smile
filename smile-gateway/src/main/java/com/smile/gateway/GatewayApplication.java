package com.smile.gateway;

import com.smile.basic.core.config.common.IgnoreUrlsConfig;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.basic.redis.constant.RedisConstant;
import com.smile.gateway.common.GatewayConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *  https://blog.csdn.net/qq_45136501/article/details/127792779
 * 如果配置了profile也就是没有spring.profiles.active=dev/test/prod/xx时候
 * 那么默认就是bootstrap.properties -> bootstrap.yml -> application.properties -> application.yml，
 * 如果中途有Nacos远程配置中心，那么Nacos中的配置会覆盖本地相同配置。
 * 如果配置了profile为dev
 * 那么加载顺序就变化了
 * bootstrap-dev.yml -> bootstrap.properties -> bootstrap.yml -> application-dev.yml -> application.properties ->
 * application.yml如果中途有Nacos远程配置中心，那么Nacos中的配置会覆盖本地相同配置。
 *
 * */

@SpringBootApplication(scanBasePackages = {GlobalConstants.BASIC_CORE_SCAN,
        RedisConstant.REDIS_MODEL_SCAN, GatewayConstants.GATEWAY_MODEL_SCAN})
public class GatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GatewayApplication.class, args);
        IgnoreUrlsConfig bean = run.getBean(IgnoreUrlsConfig.class);
        bean.getUrls().forEach(System.out::println);
    }
}
