package com.spring2x.rabbitmq.exchanges.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/2.
 */
@Component
public class FanoutConsumer {

    public static final Logger logger = LoggerFactory.getLogger(FanoutConsumer.class);

    @RabbitListener(queues = "#{fanoutRabbitElement.Queue_Fanout_1}")
    public void fanoutConsumer1(String content){
        logger.info("fanout consumer 1 receive the message:[{}]", content);
    }

    @RabbitListener(queues = "#{fanoutRabbitElement.Queue_Fanout_2}")
    public void fanoutConsumer2(String content){
        logger.info("fanout consumer 2 receive the message:[{}]", content);
    }

}
