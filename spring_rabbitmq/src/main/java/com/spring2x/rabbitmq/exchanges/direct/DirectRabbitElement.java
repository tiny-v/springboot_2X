package com.spring2x.rabbitmq.exchanges.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiny_v
 * @date 2022/1/6.
 */
@Configuration
public class DirectRabbitElement {

    //路由器 Exchange
    public static final String Exchange_Direct = "Exchange_Direct";
    //队列 Queue
    public static final String Queue_Direct_1 = "Queue_Direct_1";
    public static final String Queue_Direct_2 = "Queue_Direct_2";
    //绑定规则
    public static final String Binding_Key_Direct1 = "Binding_Key_Direct1";
    public static final String Binding_Key_Direct2 = "Binding_Key_Direct2";
    //持久化
    private static final Boolean Durable = Boolean.FALSE;

    /**
     * 声明一个路由器，类型为Direct
     * @return
     */
    @Bean
    public DirectExchange directExchange(){return new DirectExchange(Exchange_Direct, Durable, false);}
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
    //声明一个队列
    @Bean
    public Queue directQueue1(){
        return new Queue(Queue_Direct_1, Durable, false, false, null);
    }


    /**
     * 声明一个队列
     * @return
     */
    @Bean
    public Queue directQueue2(){
        return new Queue(Queue_Direct_2, Durable, false, false, null);
    }

    /**
     * 声明队列1和路由器的绑定关系
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindDirectQueue1ToDirectExchange(@Qualifier("directQueue1") Queue directQueue, @Qualifier("directExchange") DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with(Binding_Key_Direct1);
    }

    /**
     * 声明队列2和路由器的绑定关系
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindDirectQueue2ToDirectExchange(@Qualifier("directQueue2") Queue directQueue, @Qualifier("directExchange") DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with(Binding_Key_Direct2);
    }

}
