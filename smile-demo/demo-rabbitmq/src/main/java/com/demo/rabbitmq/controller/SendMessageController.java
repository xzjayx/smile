package com.demo.rabbitmq.controller;


import com.demo.rabbitmq.config.TtlQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author :xiezhi
 * @date : 2023/1/29
 */

@Slf4j
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private  RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssSSS"));
        Map<String,Object> map=new HashMap<>(16);
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    /**
     * 发送10秒的TTL业务队列
     * */
    @GetMapping("/sendttl10")
    public String sendttl10() {
        log.info("发送一条信息给10S A TTL");
        String message = "消息来自 ttl 为 10S 的队列: " + UUID.randomUUID();
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE, "XA", message.getBytes(StandardCharsets.UTF_8));
        return "ok";
    }


}
