//package com.smile.actuator.config;
//
//import java.util.UUID;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import de.codecentric.boot.admin.server.config.AdminServerProperties;
/*

1.授予对所有静态资产和登录页面的公共访问权限。
2.必须对所有其他请求进行身份验证。
3.配置登录和注销。
4.启用 HTTP 基本支持。这是 Spring Boot Admin Client 注册所必需的。
5.使用 Cookie 启用 CSRF 保护
6.为 Spring Boot Admin Client 用于（取消）注册的端点禁用 CSRF 保护。
7.禁用执行器端点的 CSRF 保护。


首先引出两个概念：Full 全模式，Lite 轻量级模式
@Configuration(proxyBeanMethods = false)
Full(proxyBeanMethods = true) :proxyBeanMethods参数设置为true时即为：Full 全模式。 该模式下注入容器中的同一个组件无论被取出多少次都是同一个bean实例，即单实例对象，在该模式下SpringBoot每次启动都会判断检查容器中是否存在该组件
Lite(proxyBeanMethods = false) :proxyBeanMethods参数设置为false时即为：Lite 轻量级模式。该模式下注入容器中的同一个组件无论被取出多少次都是不同的bean实例，即多实例对象，在该模式下SpringBoot每次启动会跳过检查容器中是否存在该组件
什么时候用Full全模式，什么时候用Lite轻量级模式？
当在你的同一个Configuration配置类中，注入到容器中的bean实例之间有依赖关系时，建议使用Full全模式
当在你的同一个Configuration配置类中，注入到容器中的bean实例之间没有依赖关系时，建议使用Lite轻量级模式，以提高springboot的启动速度和性能


 * */
//@Configuration(proxyBeanMethods = false)
//public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
//
//    private final AdminServerProperties adminServer;
//
//    private final SecurityProperties security;
//
//    public SecuritySecureConfig(AdminServerProperties adminServer, SecurityProperties security) {
//        this.adminServer = adminServer;
//        this.security = security;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));
//
//        http.authorizeRequests(
//                (authorizeRequests) -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll() // <1>
//                        .antMatchers(this.adminServer.path("/actuator/info")).permitAll()
//                        .antMatchers(this.adminServer.path("/actuator/health")).permitAll()
//                        .antMatchers(this.adminServer.path("/login")).permitAll().anyRequest().authenticated() // <2>
//        ).formLogin(
//                (formLogin) -> formLogin.loginPage(this.adminServer.path("/login")).successHandler(successHandler).and() // <3>
//        ).logout((logout) -> logout.logoutUrl(this.adminServer.path("/logout"))).httpBasic(Customizer.withDefaults()) // <4>
//                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // <5>
//                        .ignoringRequestMatchers(
//                                new AntPathRequestMatcher(this.adminServer.path("/instances"),
//                                        HttpMethod.POST.toString()), // <6>
//                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"),
//                                        HttpMethod.DELETE.toString()), // <6>
//                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**")) // <7>
//                        ))
//                .rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
//    }
//
//    // Required to provide UserDetailsService for "remember functionality"
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(security.getUser().getName())
//                .password("{noop}" + security.getUser().getPassword()).roles("USER");
//    }
//
//}

