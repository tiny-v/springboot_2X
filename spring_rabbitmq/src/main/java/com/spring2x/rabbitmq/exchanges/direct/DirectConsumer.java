package com.spring2x.rabbitmq.exchanges.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/2.
 */
@Component
public class DirectConsumer {

    public static final Logger logger = LoggerFactory.getLogger(DirectConsumer.class);

    @RabbitListener(queues = "#{directRabbitElement.Queue_Direct_1}")
    public void directConsumer1(String content){
        logger.info("direct consumer 1 receive the message:[{}]", content);
    }

    @RabbitListener(queues = "#{directRabbitElement.Queue_Direct_2}")
    public void directConsumer2(String content){
        logger.info("direct consumer 2 receive the message:[{}]", content);
    }

}
