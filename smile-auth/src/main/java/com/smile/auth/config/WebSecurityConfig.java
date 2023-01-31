package com.smile.auth.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security，允许获取公钥接口的访问
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //formLogin().and().  httpBasic().and(). httpBasic和formLogin 区别就是一个前者是登录的的页面登录，后者是一个弹窗输入用户名密码 详细区别百度
        http.csrf().disable().authorizeRequests()
                //这里是允许让所有spring 监控的端点请求可以被允许访问WebEndpointProperties.class http://localhost:9401/actuator可以访问
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                // 除了公钥接口暴露，这里的这个接口是可以允许，直接访问 http://localhost:9401/rsa/publicKey，提供jwt RSA 公钥暴露接口
                // 放开登录token令牌端点接口
                .antMatchers("/rsa/publicKey").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                // 所有的请求都需要认证
                .anyRequest().authenticated().and()
                .formLogin().disable();
    }

    /**
     * 认证管理对象
     * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码器
     * 委托方式，根据密码的前缀选择对应的encoder，例如：{bcypt}前缀->标识BCYPT算法加密；{noop}->标识不使用任何加密即明文的方式
     * @see com.smile.basic.core.enums.PasswordEncoderTypeEnum
     * 密码判读 DaoAuthenticationProvider#additionalAuthenticationChecks
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
