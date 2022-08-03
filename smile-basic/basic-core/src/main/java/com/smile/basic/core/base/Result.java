package com.smile.basic.core.base;

import com.smile.basic.core.enums.ResultEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用返回对象
 */
@Data
public class Result<T> {

    @ApiModelProperty(value = "响应码",  example = "200")
    private String  code;
    @ApiModelProperty(value = "响应信息", example = "操作成功")
    private String  message;

    @ApiModelProperty(value = "响应主体")
    private T data;

    protected Result() {
    }

    protected Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 成功返回结果
     *
     */
    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(),null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), message, data);
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(ResultEnum successCode,T data) {
        return new Result<T>(successCode.getCode(), successCode.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     */
    public static <T> Result<T> success(ResultEnum successCode) {
        return new Result<T>(successCode.getCode(), successCode.getMessage(), null);
    }


    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(ResultEnum errorCode) {
        return new Result<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(ResultEnum errorCode,T data) {
        return new Result<T>(errorCode.getCode(), errorCode.getMessage(), data);
    }



    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> failed(ResultEnum errorCode,String message) {
        return new Result<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> failed(String errorCode,String message) {
        return new Result<T>(errorCode, message, null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> failed(String errorCode,String message,T data) {
        return new Result<T>(errorCode, message, data);
    }


    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<T>(ResultEnum.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed(ResultEnum.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(ResultEnum.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage(), data);
    }



    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getMessage(), data);
    }

}
