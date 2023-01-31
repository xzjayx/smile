package com.demo.rabbitmq;

import com.smile.basic.core.constant.GlobalConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :xiezhi
 * @date : 2023/1/31
 */
@SpringBootApplication(scanBasePackages = {"com.demo.rabbitmq", GlobalConstants.BASIC_CORE_SCAN})
public class RabbitmqApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApp.class, args);
    }
}
