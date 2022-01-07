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

    @Bean
    public Queue stockQueue(){
        return new Queue(QUEUE_STOCK, true);
    }

    @Bean
    public Binding bindStockQueueToTopic(@Qualifier("stockQueue") Queue stockQueue, @Qualifier("stockTopic") TopicExchange stockTopic){
        return BindingBuilder.bind(stockQueue).to(stockTopic).with(ROUTER_STOCK);
    }

}
