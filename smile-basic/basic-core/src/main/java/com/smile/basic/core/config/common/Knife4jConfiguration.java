package com.smile.basic.core.config.common;


import cn.hutool.core.collection.CollectionUtil;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * /doc.html
 * https://doc.xiaominfo.com/knife4j/documentation/dynamicRequestParameter.html
 * https://blog.csdn.net/qingti_/article/details/124555685
 * Knife4j（Swagger2）的配置
 */

/**
 * lombok
 * @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
   无参构造器、部分参数构造器、全参构造器。
 *
 * Lombok没法实现多种参数构造器的重载。
 * 这三个注解都是用在类上的，第一个和第三个都很好理解，就是为该类产生无参的构造方法和包含所有参数的构造方法
 *
 * 第二个注解则使用类中所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法，
 * 当然，和前面几个注解一样，成员变量都是非静态的，另外，如果类中含有final修饰的成员变量，是无法使用@NoArgsConstructor注解的。

   三个注解都可以指定生成的构造方法的访问权限，同时，第二个注解还可以用@RequiredArgsConstructor(staticName="methodName")
的形式生成一个指定名称的静态方法，返回一个调用相应的构造方法产生的对象
 *
 *   自定义是否加载: 主要让网关gateway 不加载
*                     knife4j:
 *                     smile:
 *                       enable: true
 *
 * */
@Configuration
@EnableSwagger2WebMvc
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "knife4j.smile",name = "enable",havingValue = "true")
public class Knife4jConfiguration {

    /**
     * 分组名称
     */
    private String groupName = "base-knife4j";
    /**
     * 主机名
     */
    private String host = "http://java.qingtian.cn";
    /**
     * 标题
     */
    private String title = "API在线文档工具";
    /**
     * 简介
     */
    private String description = "Knife4j演示";
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private String contactName = "项目研发部";
    /**
     * 联系网址
     */
    private String contactUrl = "http://java.qingtian.cn";
    /**
     * 联系邮箱
     */
    private String contactEmail = "java@qingtian.cn";
    /**
     * 版本号
     */
    private String version = "1.0.0";

    private final OpenApiExtensionResolver openApiExtensionResolver;






    @Bean
    public Docket docket() {
        //schema
//        List<GrantType> grantTypes=new ArrayList<>();
//        //密码模式
//        String passwordTokenUrl="http://127.0.0.1:9527/auth/oauth/token";
//        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant=new ResourceOwnerPasswordCredentialsGrant(passwordTokenUrl);
//        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
//
//        OAuth oAuth=new OAuthBuilder().name("oauth2")
//                .grantTypes(grantTypes).build();
//        //context
//        //scope方位
//        List<AuthorizationScope> scopes=new ArrayList<>();
//        scopes.add(new AuthorizationScope("read","read all resources"));
//        SecurityReference securityReference=new SecurityReference("oauth2",
//                scopes.toArray(new AuthorizationScope[]{}));
//        SecurityContext securityContext=new SecurityContext(CollectionUtil.
//                newArrayList(securityReference),PathSelectors.ant("/api/**"));
//        //schemas
//        List<SecurityScheme> securitySchemes=CollectionUtil.newArrayList(oAuth);
//        //securyContext
//        List<SecurityContext> securityContexts=CollectionUtil.newArrayList(securityContext);




        String groupName = "1.0.0";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                //如果存在多个 Docket 实例，则每个实例都必须具有此方法提供的唯一 groupName。默认为“默认”。
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                //引入oauth2.0模式
                //.securityContexts(securityContexts).securitySchemes(securitySchemes)
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .build();
    }
}
