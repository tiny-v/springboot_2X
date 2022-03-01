package com.tinyv.demo.rabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny_v
 * @date 2022/2/28.
 */
@Configuration
public class HeadersRabbitConfig {

    // 路由器
    public static final String Exchange_Headers = "Exchange_Headers";

    // 队列
    public static final String Queue_Headers_1 = "Queue_Headers_1";
    public static final String Queue_Headers_2 = "Queue_Headers_2";


    private Boolean Durable = Boolean.FALSE;


    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(Exchange_Headers, Durable, false);
    }

    @Bean(name="headersQueue1")
    public Queue headersQueue1(){
        return new Queue(Queue_Headers_1, Durable, false, false);
    }

    @Bean(name="headersQueue2")
    public Queue headersQueue2(){
        return new Queue(Queue_Headers_2, Durable, false, false);
    }

    @Bean
    public Binding bind1(@Qualifier("headersQueue1") Queue queue, @Qualifier("headersExchange") HeadersExchange exchange){
        Map<String, Object> map = new HashMap();
        map.put("Queue", "headers");
        map.put("type", "any");
        return BindingBuilder.bind(queue).to(exchange).whereAny(map).match();
    }

    @Bean
    public Binding bind2(@Qualifier("headersQueue2") Queue queue, @Qualifier("headersExchange") HeadersExchange exchange){
        Map<String, Object> map = new HashMap();
        map.put("Queue", "headers");
        map.put("type", "all");
        return BindingBuilder.bind(queue).to(exchange).whereAll(map).match();
    }


}
