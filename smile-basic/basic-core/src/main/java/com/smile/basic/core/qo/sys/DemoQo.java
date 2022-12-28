package com.smile.basic.core.qo.sys;

import com.smile.basic.core.qo.base.BaseQueryQo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
* <p>
    * 
    * </p>
*
* @author smile
* @since 2022-12-27 17:11:41
*/
@Data
@ApiModel(value = "DemoQo对象", description = "Qo对象")
public class DemoQo {

    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("金额")
    private BigDecimal money;
    @ApiModelProperty("金额")
    private BigDecimal money1;
    @ApiModelProperty("流水号")
    private Long sn;
    @ApiModelProperty("是否激活")
    private Boolean active;
    @ApiModelProperty("基本查询对象")
    private BaseQueryQo baseQueryQo;

}
