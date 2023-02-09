package com.demo.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author :xiezhi
 * @date : 2023/1/29
 */

@Component
public class DirectReceiver {


    /**
     *  直连型交换机，根据消息携带的路由键将消息投递给对应队列。
     *  大致流程，有一个队列绑定到一个直连交换机上，同时赋予一个路由键 routing key 。
     *  然后当一个消息携带着路由值为X，这个消息通过生产者发送给交换机时，交换机就会根据这个路由值X去寻找绑定值也是X的队列。
     * rabbitmq中会有默认的直连交换机exchange为
     * */
    @SneakyThrows
    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    @RabbitHandler
    public void process1(Map testMessage, Message message, Channel channel) {
        System.out.println("DirectReceiver消费者收到消息process1  : " + testMessage.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
