package com.smile.api.admin.feign;

import com.smile.basic.core.base.Result;
import com.smile.basic.core.dto.sys.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiezhi
 */ //name/value:表示需要请求到的服务名，直接冲注册中心去找服务名然后解析出来的ip:port
// 直接访问不经过网关 contextId用户当前feign的bean名
@FeignClient(name = "smile-sys",contextId = "user")
public interface UserFeignClient {

    //feign 底层这里采用了自定义的apache httpClient 发送http请求，所以需要把参数给填装完毕，如何用ok http则不一定
    // 该value必须要写不能用简写方式  https://blog.csdn.net/he__xu/article/details/120892236
    @GetMapping("/user/loadUserByUsername/{username}")
    Result<UserDTO> loadUserByUsername(@PathVariable("username") String username);
}
