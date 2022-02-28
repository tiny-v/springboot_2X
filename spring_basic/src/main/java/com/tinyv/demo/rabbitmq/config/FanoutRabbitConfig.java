package com.tinyv.demo.rabbitmq.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiny_v
 * @date 2022/2/28.
 */
@Configuration
public class FanoutRabbitConfig {

    public static final String Exchange_Fanout = "Exchange_Fanout";

    public static final String Queue_Fanout_1 = "Queue_Fanout_1";
    public static final String Queue_Fanout_2 = "Queue_Fanout_2";
    @Bean
    public FanoutExchange fanoutExchange(){return new FanoutExchange(Exchange_Fanout, true, false);}

}
