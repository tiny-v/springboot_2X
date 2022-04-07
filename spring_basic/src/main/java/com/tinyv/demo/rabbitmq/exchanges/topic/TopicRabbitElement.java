package com.tinyv.demo.rabbitmq.exchanges.topic;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiny_v
 * @date 2022/2/28.
 */
@Configuration
public class TopicRabbitElement {

    //Exchange
    public static final String Exchange_Topic = "Exchange_Topic";

    //Queue
    public static final String Queue_Topic_1 = "Queue_Topic_1";
    public static final String Queue_Topic_2 = "Queue_Topic_2";

    //Binding
    // *:表示一级匹配   #：表示多级匹配
    public static final String Binding_Key_Topic1 = "#.topic1.#";
    public static final String Binding_Key_Topic2 = "*.topic2.*";

    //持久化
    private static final Boolean Durable = Boolean.FALSE;

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Exchange_Topic, true, false);
    }

    //声明一个队列
    @Bean
    public Queue topicQueue1(){
        return new Queue(Queue_Topic_1, Durable, false, false, null);
    }

    //声明一个队列
    @Bean
    public Queue topicQueue2(){
        return new Queue(Queue_Topic_2, Durable, false, false, null);
    }

    @Bean
    public Binding bindTopicQueue1ToTopicExchange(@Qualifier("topicQueue1") Queue topicQueue, @Qualifier("topicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Binding_Key_Topic1);
    }

    @Bean
    public Binding bindTopicQueue2ToTopicExchange(@Qualifier("topicQueue2") Queue topicQueue, @Qualifier("topicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Binding_Key_Topic2);
    }

}
