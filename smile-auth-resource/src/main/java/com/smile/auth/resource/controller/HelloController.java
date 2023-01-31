package com.smile.auth.resource.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * Created by macro on 2020/6/19.
 */
@RestController
@RequestMapping("/hello")
@Api(tags = "hello测试模块")
public class HelloController {

    @GetMapping("/adminHello")
    @ApiOperation(value = "测试adminHello")
    public String hello() {
        return "Hello World. admin";
    }

}
