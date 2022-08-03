package com.smile.auth.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * Created by macro on 2020/6/19.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/adminHello")
    public String hello() {
        return "Hello World. admin";
    }

}
