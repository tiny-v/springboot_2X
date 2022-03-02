package com.tinyv.demo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/3/2.
 */
@Component
public class DlxRabbitConsumer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    //Direct
    @RabbitListener(queues = "#{dlxRabbitConfig.DLX_Queue}")
    public void dlxConsumer(Message message){
        logger.info("dlx consumer receive the message:[{}]", message);
    }

}
