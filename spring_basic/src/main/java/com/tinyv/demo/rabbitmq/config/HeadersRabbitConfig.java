package com.tinyv.demo.rabbitmq.config;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    // BindingKey
    public static final String Binding_Key_Headers = "Binding_Key_Headers";


    @Bean
    public HeadersExchange headersTopic(){
        return new HeadersExchange(Exchange_Headers, true, false);
    }

}
