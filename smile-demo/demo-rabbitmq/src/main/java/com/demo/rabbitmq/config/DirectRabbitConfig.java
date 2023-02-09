package com.demo.rabbitmq.config;

import com.demo.rabbitmq.enumeration.RoutingEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :xiezhi
 * @date : 2023/1/29
 * https://www.cnblogs.com/cyq1162/p/16609207.html
 */

@Configuration
public class DirectRabbitConfig {

    @Qualifier("TestDirectExchange")
    @Autowired 
    private DirectExchange directExchange;

    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        //一般设置一下队列的持久化就好,其余两个就是默认false
        /**
         * durable: true 即将相关队列的消息、或交换机的绑定信息等内容，写入硬盘备份，以便系统故障或重启时，仍可以恢复原来的状态，保障信息不丢失。
         * 缺点：大量的硬盘写入，会对IO造成不小的影响，因此RabbitMQ的部署环境，应该尽量避免和IO读写频繁的应用在同一磁盘上，比如数据库等。
         * exclusive: true 一般为false
         * 如果该参数为 true，则该队列仅允许创建它的连接进行写入或读取，同时当该链接关闭时，该队列被删除。
         * 该参数为true时，持久化参数上一个参数是无效的，因为链接关闭即释放队列。
         * autoDelete:是否自动删除一般为false，如果true当没有生产者或者消费者使用此队列，该队列会自动删除。
         * */
        return new Queue("TestDirectQueue",true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange",true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }



}
