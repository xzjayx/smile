package com.smile.basic.redis.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author :xiezhi
 * @date : 2023/2/21
 * 自定义日志Aop切点逻辑
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    //@Pointcut("execution(public * com.zking..controller.*.*(..))")
    @Pointcut("execution(* *..*Controller.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("开始服务:{}", request.getRequestURL().toString());
        log.info("客户端IP :{}" , request.getRemoteAddr());
        log.info("参数值 :{}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("返回值 : {}" , ret);
    }



}
