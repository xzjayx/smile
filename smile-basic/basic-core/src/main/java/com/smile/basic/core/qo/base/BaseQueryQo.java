package com.smile.basic.core.qo.base;

import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
*  通用查询对象，包括常用的分页对象
 *  https://blog.csdn.net/qq_44099797/article/details/121181384 mybatis-plus page对象
* */
@Data
@ApiModel("基本查询对象")
public class BaseQueryQo {

    @ApiModelProperty(value = "当前页码page",example = "1")
    private Long current = 1L;

    @ApiModelProperty(value = "每一页显示条数", example = "10")
    private Long size = 10L;

}
