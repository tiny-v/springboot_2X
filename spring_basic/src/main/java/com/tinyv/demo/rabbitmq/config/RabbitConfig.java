package com.tinyv.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiny_v
 * @date 2022/1/6.
 */
@Configuration
public class RabbitConfig {

    public static final String TOPIC_STOCK = "STOCK_TOPIC";

    public static final String QUEUE_STOCK = "STOCK_QUEUE";

    public static final String ROUTER_STOCK = "stock.*";

    @Bean
    public TopicExchange stockTopic(){
        return new TopicExchange(TOPIC_STOCK, true, false);
    }

    /**
     * name(队列名称): 不能为null， 如果为“”， 则由broker自己生成一个名称
     *
     * durable(持久化):  true(持久化) | false(非持久化)
     * 持久化队列会被保存在磁盘中，固定并持久的存储，当RabbitMQ服务重启后，该队列仍会保持原来的状态在RabbitMQ中被管理。
     * 而非持久化队列不会被保存在磁盘中，Rabbit服务重启后队列就会消失，其使用速度比持久化队列快；
     *
     * exclusive:
     *
     * autoDelete(自动删除队列):
     * 如果我们将其上述参数autoDelete置为 true 的话，那么当消费者断开连接时，队列将会被删除。
     * 自动删除队列允许的消费者没有限制，也就是说当这个队列上最后一个消费者断开连接才会执行删除
     *
     * arguments(其它参数):
     * */
    @Bean
    public Queue stockQueue(){
        return new Queue(QUEUE_STOCK, true, false, false, null);
    }

    @Bean
    public Binding bindStockQueueToTopic(@Qualifier("stockQueue") Queue stockQueue, @Qualifier("stockTopic") TopicExchange stockTopic){
        return BindingBuilder.bind(stockQueue).to(stockTopic).with(ROUTER_STOCK);
    }

}
