package com.smile.basic.core.config.exception;


import com.smile.basic.core.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 当前全局异常处理
 * https://blog.csdn.net/qq_36829919/article/details/101210250
 * https://www.csdn.net/tags/MtTaMg0sODA3NTkzLWJsb2cO0O0O.html
 * 可以搭配断言使用
* */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAction {

    /**
     * ExceptionHandler(CommonRuntimeException.class)
     * 表示当前处理器只处理CommonRuntimeException异常
     * @ExceptionHandle注解——只能在控制器中定义异常处理方法。
     * @ControllerAdvice+@ExceptionHandler——增强控制前Controller实现异常拦截。
     */
    @ExceptionHandler(CommonRuntimeException.class)
    @ResponseBody
    public Result<Object> handlerException(CommonRuntimeException e){
        log.error("throw Exception",e);
        return Result.failed(e.getCode(), e.getMessage(),e.getO());
    }

}
