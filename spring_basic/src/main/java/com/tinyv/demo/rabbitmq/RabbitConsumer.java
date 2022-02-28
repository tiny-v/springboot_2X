package com.tinyv.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/1/7.
 */
@Component
public class RabbitConsumer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    //Direct
    @RabbitListener(queues = "#{directRabbitConfig.Queue_Direct_1}")
    public void directConsumer1(String content){
        logger.info("direct consumer 1 receive the message:[{}]", content);
    }

    @RabbitListener(queues = "#{directRabbitConfig.Queue_Direct_2}")
    public void directConsumer2(String content){
        logger.info("direct consumer 2 receive the message:[{}]", content);
    }

    //Topic
    @RabbitListener(queues = "#{topicRabbitConfig.Queue_Topic_1}")
    public void topicConsumer1(String content){
        logger.info("topic consumer 1 receive the message:[{}]", content);
    }


    @RabbitListener(queues = "#{topicRabbitConfig.Queue_Topic_2}")
    public void topicConsumer2(String content){
        logger.info("topic consumer 2 receive the message:[{}]", content);
    }

}
