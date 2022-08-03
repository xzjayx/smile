package com.smile.basic.core.config.exception;


import com.smile.basic.core.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonRuntimeException extends RuntimeException{

    private String code;

    private Object o;


    /**
     * 自己临时自定义状态码和状态信息
     * @param code 状态
     * @param message 状态信息
     */
    public CommonRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 自己临时自定义状态码和状态信息
     * @param code 状态
     * @param message 状态信息
     */
    public CommonRuntimeException(String code, String message,Object o) {
        super(message);
        this.code = code;
        this.o = o;
    }

    /**
     *
     * @param resultEnum  从枚举对象中获取状态码和状态信息
     */
    public CommonRuntimeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    /**
     *
     * @param resultEnum  从枚举对象中获取状态码和状态信息
     */
    public CommonRuntimeException(ResultEnum resultEnum,Object o) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.o = o;
    }

}
