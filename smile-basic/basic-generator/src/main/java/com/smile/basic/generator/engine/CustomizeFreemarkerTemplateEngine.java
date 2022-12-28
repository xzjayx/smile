package com.smile.basic.generator.engine;


import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * 自定义FreeMarkerTemplateEngine模板
 * @author mi
 */
public class CustomizeFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    /**
     * 自定义输出自定义模板文件
     *
     * @param customFile 自定义配置模板文件信息  builder.customFile
     * @param tableInfo  当前生成的表信息
     * @param objectMap  渲染数据
     * @since 3.5.1
     */
    @Override
    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        Map<String, String> customFilePath = (Map<String, String>)objectMap.get("customFilePathMap");
        customFile.forEach((key, value) -> {
            String otherPath = customFilePath.get(key);
            String fileName = String.format((otherPath + File.separator + "%s"), tableInfo.getEntityName().concat(key));
            //getConfigBuilder().getInjectionConfig().isFileOverride()
            outputFile(new File(fileName), objectMap, value, true);
        });
    }

    /*protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = getPathInfo(OutputFile.other);
        customFile.forEach((key, value) -> {
            String fileName = String.format((otherPath + File.separator + entityName + File.separator + "%s"), key);
            outputFile(new File(fileName), objectMap, value, getConfigBuilder().getInjectionConfig().isFileOverride());
        });
    }*/

}
