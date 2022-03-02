package com.tinyv.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny_v
 * @date 2022/3/2.
 * 死信队列配置
 */
@Component
public class DlxRabbitConfig {

    /*********************************** 声明死信路由器和死信队列 *************************************/

    /**死信路由器*/
    private static final String DLX_Exchange = "DLX_Exchange";

    /**死信队列*/
    public static final String DLX_Queue = "sms.dlx.queue";

    /**路由*/
    private static final String DLX_Binding = "sms.dlx.binding";

    @Bean(name="deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DLX_Exchange, false, false);
    }

    @Bean(name="deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(DLX_Queue, false, false, false, null);
    }

    @Bean
    public Binding bindDlx(@Qualifier("deadLetterQueue") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DLX_Binding);
    }


    /*********************************** 声明超时路由器和超时队列 *************************************/

    /**超时路由器*/
    public static final String TTL_Exchange = "TTL_Exchange";

    /**超时队列*/
    private static final String TTL_Queue = "sms.ttl.queue";

    /**路由规则*/
    public static final String TTL_Binding = "sms.ttl.binding";

    @Bean(name="ttlExchange")
    public DirectExchange ttlExchange(){
        return new DirectExchange(TTL_Exchange, false, false);
    }

    @Bean(name="ttlQueue")
    public Queue ttlQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-message-ttl 队列中的消息超时时间
        args.put("x-message-ttl", 3000);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DLX_Exchange);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DLX_Binding);
        return new Queue(TTL_Queue, false, false, false, args);
    }

    @Bean
    public Binding bindTtl(@Qualifier("ttlQueue") Queue queue, @Qualifier("ttlExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(TTL_Binding);
    }


    /*********************************** 最大长度队列 *************************************/

    /**超时队列*/
    private static final String Max_Size_Queue = "sms.max.queue";

    /**路由规则*/
    public static final String Max_Size_Binding = "sms.max.binding";

    @Bean(name="maxSizeQueue")
    public Queue maxSizeQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-max-length 设置队列最大长度
        args.put("x-max-length", 10);
        //x-overflow : 队列中的消息溢出后如何处理； 有两种可选策略( 拒绝策略[reject-publish]  && 丢弃头部策略[drop-head] )
        //此处要注意：可以不设置此参数，默认为drop-head. 但如果设置成了 reject-publish， 被拒绝的消息并不会进入死信队列
        args.put("x-overflow", "drop-head");
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DLX_Exchange);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DLX_Binding);
        return new Queue(Max_Size_Queue, false, false, false, args);
    }

    @Bean
    public Binding bindMaxSize(@Qualifier("maxSizeQueue") Queue queue, @Qualifier("ttlExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(Max_Size_Binding);
    }

}
