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
 * @date 2022/2/28.
 */
@Configuration
public class BatchRabbitConfig {

    //Exchange
    public static final String Batch_Exchange = "Batch_Topic";

    //Queue
    public static final String Batch_Queue_1 = "Batch_Queue_1";

    //Binding
    // *:表示一级匹配   #：表示多级匹配
    public static final String Binding_Key_Topic1 = "#.batch.#";

    //持久化
    private static final Boolean Durable = Boolean.TRUE;

    @Bean
    public TopicExchange batchExchange(){
        return new TopicExchange(Batch_Exchange, Durable, false);
    }

    //声明一个队列
    @Bean
    public Queue batchQueue1(){
        return new Queue(Batch_Queue_1, Durable, false, false, null);
    }


    @Bean
    public Binding bingBatchQueue(@Qualifier("batchQueue1") Queue topicQueue, @Qualifier("batchExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Binding_Key_Topic1);
    }

}
