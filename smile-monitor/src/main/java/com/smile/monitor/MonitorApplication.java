package com.smile.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :xiezhi
 * @date : 2023/9/4
 * https://www.jianshu.com/p/05ee63c5b932
 * https://itsaysay.blog.csdn.net/article/details/128029153 监控发送通知自定义
 * 实际操作意义不大，有专门的运维ZABBIX等来运维人员来监控服务运行情况
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }

}
