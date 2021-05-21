package com.tinyv.demo.business.mq;

import com.rabbitmq.client.Channel;
import com.tinyv.demo.global.config.RabbitMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author mayue
 * @date 2021/5/10.
 */
@Configuration
public class MqConsumer {


    //监听image队列
    @RabbitListener(queues = {RabbitMQConfig.Image_Queue})
    public void image_consumer(Object msg, Message message, Channel channel) {
        System.out.println("message:"+message);
        System.out.println("channel:"+channel);
        System.out.println("msg" + msg);
    }




}
