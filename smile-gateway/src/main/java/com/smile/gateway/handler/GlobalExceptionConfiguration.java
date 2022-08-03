package com.smile.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.basic.core.base.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 可能不是最好解决方案  https://blog.csdn.net/weixin_44600430/article/details/106407415
 * 参见https://blog.csdn.net/Cy_LightBule/article/details/88419093
 *
 * @date 2020/5/23
 * <p>
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 */
/*@Slf4j
@Order(-1)
@RequiredArgsConstructor
@Component
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {


    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set_json响应
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //是否响应状态异常
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        return response
            .writeWith(Mono.fromSupplier(() -> {
                DataBufferFactory bufferFactory = response.bufferFactory();
                try {
                    //返回json异常原因给前端
                    return bufferFactory.wrap(objectMapper.writeValueAsBytes(Result.failed(ex.getMessage())));
                } catch (JsonProcessingException e) {
                    log.warn("Error writing response", ex);
                    return bufferFactory.wrap(new byte[0]);
                }
            }));
    }
}*/


