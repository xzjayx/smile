package com.smile.sys;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.smile.sys.common.SysConstants;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.sys.config.SysIgnoreUrls;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = {GlobalConstants.BASIC_CORE_SCAN, SysConstants.SMILE_SYS_SCAN})
public class SysApplication {

    //TODO 1 完善user配合knife4j,配合版本接口文档和参数,并且自定义模板
    // 2 对于Transactional注解的搭配到当单体事务配合mysql的实现
    // 3 完善oauth2.0 查询到数据库
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SysApplication.class, args);
        SysIgnoreUrls bean = run.getBean(SysIgnoreUrls.class);
        bean.getUrls().forEach(System.out::println);
        System.out.println(bean.getCheck());
        OpenApiExtensionResolver action = run.getBean(OpenApiExtensionResolver.class);
        System.out.println(action);

    }
}
