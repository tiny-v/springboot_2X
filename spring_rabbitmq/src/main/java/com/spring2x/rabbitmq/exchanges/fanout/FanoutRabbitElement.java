package com.spring2x.rabbitmq.exchanges.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiny_v
 * @date 2022/2/28.
 */
@Configuration
public class FanoutRabbitElement {

    /**
     * 广播模式 --  交换机直接把消息广播给所有绑定的队列
     */
    public static final String Exchange_Fanout = "Exchange_Fanout";

    public static final String Queue_Fanout_1 = "Queue_Fanout_1";
    public static final String Queue_Fanout_2 = "Queue_Fanout_2";

    private Boolean Durable = Boolean.FALSE;

    @Bean
    public FanoutExchange fanoutExchange(){return new FanoutExchange(Exchange_Fanout, true, false);}

    @Bean
    public Queue fanoutQueue1(){
        return new Queue(Queue_Fanout_1, Durable, false, false, null);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue(Queue_Fanout_2, Durable, false, false, null);
    }

    @Bean
    public Binding bindingQueue1(@Qualifier("fanoutQueue1") Queue queue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingQueue2(@Qualifier("fanoutQueue2") Queue queue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
