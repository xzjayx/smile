package com.demo.test;

import com.smile.basic.core.constant.GlobalConstants;
import com.smile.basic.weblog.starter.aspect.WebLogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 */
@SpringBootApplication(scanBasePackages = {"com.demo.test", GlobalConstants.BASIC_CORE_SCAN})
public class DemoTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoTest.class, args);
        WebLogAspect aspect = context.getBean(WebLogAspect.class);
        System.out.println(aspect);
    }


}
