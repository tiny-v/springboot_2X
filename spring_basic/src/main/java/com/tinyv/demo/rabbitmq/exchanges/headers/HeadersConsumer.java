package com.tinyv.demo.rabbitmq.exchanges.headers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/2.
 */
@Component
public class HeadersConsumer {

    public static final Logger logger = LoggerFactory.getLogger(HeadersConsumer.class);

    @RabbitListener(queues = "#{headersRabbitElement.Queue_Headers_1}")
    public void headersConsumer1(String content){
        logger.info("headers consumer 1 receive the message:[{}]", content);
    }

    @RabbitListener(queues = "#{headersRabbitElement.Queue_Headers_2}")
    public void headersConsumer2(String content){
        logger.info("headers consumer 2 receive the message:[{}]", content);
    }

}
