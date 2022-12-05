package com.smile.basic.generator.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * mybatis-plus 代码生成封装工具类
 * */
public class GeneratorUtil {


    private static final String url = "jdbc:mysql://192.168.56.10:3306/smile_sys?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
    private static final String username = "root";
    private static final String password = "1qaz2wsx@!";



    public static String slashToPoint(String str){
        if(StrUtil.isNotBlank(str)){
            return str.replace("/", ".");
        }
        return str;
    }


    public static DataSourceConfig.Builder createDataSource(){
        return new DataSourceConfig.Builder(
                url, username, password);
    }

    public static DataSourceConfig.Builder createDataSource(String url,String username,String password){
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder(
                url, username, password);
//                .dbQuery(new MySqlQuery())
//                .schema("mybatis-plus")
//                .typeConvert(new MySqlTypeConvert())
//                .keyWordsHandler(new MySqlKeyWordsHandler());
        return dataSourceConfig;
    }
}
