package com.smile.sys.config;


import com.smile.basic.core.constant.EnvConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mi
 */

@Configuration
@ConfigurationProperties(prefix = EnvConstants.SYS_IGNORE_URL_PREFIX)
@Getter
@Setter
public class SysIgnoreUrls {

    /**
     * 因为和gateway有冲突性，则这里做一些标志性判断自定义处理逻辑
     * 1 以Sys Ignore URLs 和 Gateway Ignore URL，取两集合的并集(去重)
     * 2 以Sys Ignore URLs 为准
     * 3 以Gateway Ignore URLs 为准
     * 4 以Sys Ignore URLs 和 Gateway Ignore URL，取两集合的交集，取出相同的URL
     * default EnvConstants.CHECK_DEFAULT
     * */
    private String check;

    private List<String> urls;

}
