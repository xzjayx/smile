package com.smile.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author smile
 * @since 2022-12-27 17:11:41
 */
@Data
@TableName("sys_demo")
@ApiModel(value = "Demo实体对象", description = "")
public class Demo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private String id;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("金额")
    @TableField("money")
    private BigDecimal money;

    @ApiModelProperty("金额")
    @TableField("money1")
    private BigDecimal money1;

    @ApiModelProperty("流水号")
    @TableField("sn")
    private Long sn;

    @ApiModelProperty("是否激活")
    @TableField("active")
    private Boolean active;


}
