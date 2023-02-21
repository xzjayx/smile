package com.smile.basic.redis.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 */

//@ConfigurationProperties("zzcloud.weblog")
public class WebLogProperties implements Serializable {

    public Boolean enabled;  //Boolean封装类，默认为null

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

