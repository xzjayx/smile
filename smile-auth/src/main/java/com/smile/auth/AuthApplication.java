package com.smile.auth;

import com.smile.auth.common.AuthConstants;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.basic.redis.constant.RedisConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = GlobalConstants.SMILE_API_SCAN)
@SpringBootApplication(scanBasePackages = {RedisConstant.REDIS_MODEL_SCAN, AuthConstants.AUTH_MODEL_SCAN})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
