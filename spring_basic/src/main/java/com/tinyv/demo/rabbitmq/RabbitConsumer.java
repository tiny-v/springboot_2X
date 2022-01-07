package com.tinyv.demo.rabbitmq;

import com.tinyv.demo.BasicApps;
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

    public static final Logger logger = LoggerFactory.getLogger(BasicApps.class);

    @RabbitListener(queues = "#{rabbitConfig.QUEUE_STOCK}")
    public void stockInfoConsumer1(String content){
        logger.info("Stock Consumer1 receive the message:[{}]", content);
    }


    @RabbitListener(queues = "#{rabbitConfig.QUEUE_STOCK}")
    public void stockInfoConsumer2(String content){
        logger.info("Stock Consumer2 receive the message:[{}]", content);
    }

}
