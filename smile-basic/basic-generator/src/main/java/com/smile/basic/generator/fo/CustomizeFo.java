package com.smile.basic.generator.fo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomizeFo {

    /**
     * 小写转换entity
     * */
    private String capEntity;

    /**
     * 小写转换
     * */
    private String capServiceName;

    /**
     * qo查询对象package
     * */
    private String qoPackage;


}
