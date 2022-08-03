package com.smile.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author smile
 * @since 2022-05-24 14:42:29
 */
@Data
@TableName("sys_user")
@ApiModel(value = "User实体对象", description = "用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "Id",type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    /**
     *  tinyint(1) 映射为Boolean  @see com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert
     * */
    @ApiModelProperty("启动状态")
    @TableField("enable_status")
    private Boolean enableStatus;



}
