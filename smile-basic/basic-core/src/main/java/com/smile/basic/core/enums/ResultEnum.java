package com.smile.basic.core.enums;

import com.smile.basic.core.base.IBaseEnum;
import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 */
@Getter
public enum ResultEnum implements IBaseEnum<String> {

    OBJECT_NOT_NULL("500","对象不能为空"),


    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    VALIDATE_FAILED("503", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限");




    private final String code;

    private final String message;



    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
