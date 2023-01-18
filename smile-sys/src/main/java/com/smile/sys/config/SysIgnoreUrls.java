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
 *
 * 暂时废弃 对应配置文件也废弃，统一入口白名单，从gateway入口来配置
 * @see com.smile.basic.core.config.common.IgnoreUrlsConfig
 * #smile:
 * #  sys:
 * #    #配置白名单路径,专供admin模块服务
 * #    ignore:
 * #      check: "1"
 * #      urls:
 * #        - "/actuator/**"
 * #        - "/doc.html"
 * #        - "/webjars/**"
 * #        - "/swagger-resources/**"
 * #        - "/v2/api-docs"
 * #        - "/favicon.ico"
 * #        - "/user/**"
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
