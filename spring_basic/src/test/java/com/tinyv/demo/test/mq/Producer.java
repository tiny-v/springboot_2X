package com.tinyv.demo.test.mq;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.service.impl.CacheServiceImpl;
import com.tinyv.demo.global.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mayue
 * @date 2021/5/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class, CacheServiceImpl.class})
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMqTest() {
        //第一个参数：发送的队列 第二个参数： 发送的信息
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_RESOURCE_IMAGE,
                RabbitMQConfig.Dispatch_Image_Routing_Key,
                "hello spring boot rabbitmq"
        );
    }

}
