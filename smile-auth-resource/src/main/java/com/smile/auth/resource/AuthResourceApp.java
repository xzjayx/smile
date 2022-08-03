package com.smile.auth.resource;

import com.smile.basic.core.constant.GlobalConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.smile.auth.resource", GlobalConstants.BASIC_CORE_SCAN})
public class AuthResourceApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceApp.class, args);
    }
}
