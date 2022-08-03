package com.smile.gateway;

import com.smile.basic.core.config.common.IgnoreUrlsConfig;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.basic.redis.constant.RedisConstant;
import com.smile.gateway.common.GatewayConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * https://www.csdn.net/tags/NtjacgzsMzM2NTEtYmxvZwO0O0OO0O0O.html
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
