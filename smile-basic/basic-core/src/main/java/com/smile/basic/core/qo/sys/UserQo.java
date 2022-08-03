package com.smile.basic.core.qo.sys;

import com.smile.basic.core.qo.base.BaseQueryQo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User查询对象", description = "用户信息表查询对象")
public class UserQo {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    /**
     *  tinyint(1) 映射为Boolean  @see com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert
     * */
    @ApiModelProperty("启动状态")
    private Boolean enableStatus;

    @ApiModelProperty("基本查询对象")
    private BaseQueryQo baseQueryQo;
}
