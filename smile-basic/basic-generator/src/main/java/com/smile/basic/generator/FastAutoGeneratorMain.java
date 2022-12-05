package com.smile.basic.generator;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.smile.basic.generator.engine.CustomizeFreemarkerTemplateEngine;
import com.smile.basic.generator.fo.CustomizeFo;
import com.smile.basic.generator.util.GeneratorUtil;

import java.util.*;

/**
 * mybatis-plus 自定生成crud
 * https://baomidou.com/pages/779a6e/#%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8
 * https://baomidou.com/pages/981406/#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig
 * */
public class FastAutoGeneratorMain {


    public static void main(String[] args) {
        //指定model名，约定大于配置的原则，model后续会凭借,每个模块前面都要带admin开头
        String model = "sys";
        //指定输出目录
        String sourceDir = System.getProperty("user.dir");
        String outPutDir = "/smile-".concat(model);
        String srcMainJava = "/src/main/java";
        String packageName = "com.smile.".concat(model);
        /**
         * ------------ 基础Java模块常量设置完毕 下面为自定义的模板用到的常量-------------------------------
         * */
        String basicModelDir = "/smile-basic/basic-core";
        String basicPackageNameDir = "/com/smile/basic/core";
        String outQoDir = basicPackageNameDir.concat("/qo/").concat(model);




        //customMap 和 customFile关系,key对应都要一样的，customMap设置输出path customFile设置模板文件
        Map<String, Object> customMap = new HashMap<>(16);
        Map<String, String> customFilePathMap = new HashMap<>(16);
        //vue 前端的路径
        //customFilePathMap.put("vueFile", "/src/main/resources/vue");
        //Java Qo的路径
        customFilePathMap.put("Qo.java", sourceDir.concat(basicModelDir).concat(srcMainJava).concat(outQoDir));
        customMap.put("customFilePathMap", customFilePathMap);
        // 自定义模板文件，key为文件名称，value为模板路径做测试的后面会改成前端vue模板编写
        Map<String, String> customFile = new HashMap<>(16);
        customFile.put("Qo.java", "/templates/entityQo.java.ftl");






        List<String> tables = new ArrayList<>();
        tables.add("demo");
        //tables.add("p_question");
        //tables.add("p_answer");
        //tables.add("p_correct");



        FastAutoGenerator.create(GeneratorUtil.createDataSource())
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("smile")
                            //生成完毕之后禁止打开输出目录
                            .disableOpenDir()
                            // 开启 swagger 模式
                            .enableSwagger()
                            // 注释日期  默认值: yyyy-MM-dd
                            .commentDate(DatePattern.NORM_DATETIME_PATTERN)
                            // 指定输出目录
                            .outputDir(sourceDir.concat(outPutDir).concat(srcMainJava));
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent(packageName)
                            // 设置父包模块名
                            //.moduleName("smile-core")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.Impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, sourceDir
                                    .concat(outPutDir).concat("/src/main/resources/mapper")));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tables)
                            .addTablePrefix(model.concat("_"))
                            .serviceBuilder()
                            //.fileOverride()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            //.fileOverride()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .fileOverride()
                            .enableRestStyle()
                            .mapperBuilder()
                            //生成通用的resultMap
                            .enableBaseResultMap()
                            // 启用 BaseColumnList
                            .enableBaseColumnList()
                            .enableMapperAnnotation()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")  // "%sDao"
                            .formatXmlFileName("%sMapper");
                })
                .templateConfig(builder -> {
                    // 实体类使用我们自定义模板 引入entity @data
                    builder.entity("templates/entity.java");
                    builder.controller("templates/controller.java");
                })
                //自定义模板生成前端代码vue
                .injectionConfig(builder -> {
                    builder.beforeOutputFile((TableInfo tableInfo, Map<String, Object> objectMap) -> {
                        System.out.println("tableInfo: " + tableInfo.getEntityName() +
                                " objectMap: " + objectMap.size());
                        //可以再这里动态定制service，设置一些模板生成属性,例如下面自定义属性customizefo首字母转小写
                        String capServiceName = String.valueOf(tableInfo.getServiceName().toCharArray()[0] += 32)
                                .concat(tableInfo.getServiceName().substring(1));
                        String capEntity = String.valueOf(tableInfo.getEntityName().toCharArray()[0] += 32)
                                .concat(tableInfo.getEntityName().substring(1));
                        CustomizeFo customizefo = CustomizeFo.builder().capServiceName(capServiceName)
                                .capEntity(capEntity).qoPackage(GeneratorUtil.slashToPoint(outQoDir).substring(1)).build();
                        objectMap.put("customizefo", customizefo);
                    }).customMap(customMap).customFile(customFile);
                    //自定义配置给定的一个后面可以自行去拿出来用于自定义参数等等
                    // 这里涉及到前端的vue代码的生成
                })
                //new FreemarkerTemplateEngine()
                .templateEngine(new CustomizeFreemarkerTemplateEngine())
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();





//        FastAutoGenerator.create("url", "username", "password")
//                .globalConfig(builder -> {
//                    builder.author("baomidou") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D://"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();

    }

   /** private static void accept(TableInfo tableInfo, Map<String, Object> objectMap) {
        System.out.println("tableInfo: " + tableInfo.getEntityName() +
                " objectMap: " + objectMap.size());
        //可以再这里动态定制service，设置一些模板生成属性,例如下面自定义属性customizefo首字母转小写
        String capServiceName = String.valueOf(tableInfo.getServiceName().toCharArray()[0] += 32)
                .concat(tableInfo.getServiceName().substring(1));
        CustomizeFo customizefo = CustomizeFo.builder().capServiceName(capServiceName)
                .qoPackage(GeneratorUtil.slashToPoint(outQoDir)).build();
        objectMap.put("customizefo", customizefo);
    }*/
}
