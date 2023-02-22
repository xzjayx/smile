package com.smile.basic.weblog.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 * @see com.smile.basic.weblog.starter.config.WebLogAutoConfiguration
 * ConditionalOnProperty(prefix = "smile.weblog",value = "enabled") 对应当前类的enabled的value
 *
 */

@ConfigurationProperties("smile.weblog")
public class WebLogProperties implements Serializable {


    /**
     * 是否开启Aop日志功能
     * */
    public Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

