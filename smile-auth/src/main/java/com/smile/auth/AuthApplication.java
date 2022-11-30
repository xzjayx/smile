package com.smile.auth;

import com.smile.auth.common.AuthConstants;
import com.smile.basic.core.constant.GlobalConstants;
import com.smile.basic.redis.constant.RedisConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * #https://blog.csdn.net/qq_45136501/article/details/127792779  https://blog.csdn.net/m0_67266787/article/details/124033785 配置文件的加载顺序
 * #http://www.qb5200.com/article/476665.html springboot结合maven配置不同环境的profile，
 * #如果项目中有bootstrap.yml系统参数配置则不适用 @see https://blog.csdn.net/Amy126/article/details/106016308/
 * 如果项目中非要使用，则可以参考 Maven的一些filtering特性
 *
 *
 * VM -Dspring.profiles.active=dev
 *
 * 启动文件加载路径本地启动
 * 如果配置了profile如果不配置那么默认profile是dev
 * 那么按照情况也是bootstrap.yml先加载，然后在加载对应的bootstrap-xxx.yml
 *
 */
@EnableFeignClients(basePackages = GlobalConstants.SMILE_API_SCAN)
@SpringBootApplication(scanBasePackages = {RedisConstant.REDIS_MODEL_SCAN, AuthConstants.AUTH_MODEL_SCAN})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
