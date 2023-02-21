package com.demo.test;

import com.smile.basic.core.constant.GlobalConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 */
@SpringBootApplication(scanBasePackages = {"com.demo.test", GlobalConstants.BASIC_CORE_SCAN})
public class DemoTest {

    public static void main(String[] args) {
        SpringApplication.run(DemoTest.class, args);
    }


}
