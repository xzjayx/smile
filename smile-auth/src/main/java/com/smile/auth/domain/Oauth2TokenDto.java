package com.smile.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2获取Token返回信息封装
 *
 * @author xiezhi
 * @date 2020/7/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel("Oauth2获取Token返回信息封装")
public class Oauth2TokenDto {
    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌token")
    private String token;
    /**
     * 刷新令牌
     */
    @ApiModelProperty(value = "刷新令牌token")
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    @ApiModelProperty(value = "访问令牌头前缀")
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    @ApiModelProperty(value = "令牌有效时间（秒）")
    private int expiresIn;
}
