package com.tinyv.demo.test.rabbitmq;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.rabbitmq.config.DirectRabbitConfig;
import com.tinyv.demo.rabbitmq.producer.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/1/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
public class RabbitProducerTest {

    @Autowired
    public RabbitProducer rabbitProducer;

    @Test
    public void sendDirectMessage(){
        for(int i=0 ;i<1000000; i++){
            rabbitProducer.sendToDirectExchange("hello world direct 1 : " + i, DirectRabbitConfig.Binding_Key_Direct1);
            //rabbitProducer.sendToDirectExchange("hello world direct 2 : " + i, DirectRabbitConfig.Binding_Key_Direct2);
        }
    }

    @Test
    public void sendTopicMessage(){
        rabbitProducer.sendToTopicExchange("hello world topic 1 : ", "a.topic1.a");
        rabbitProducer.sendToTopicExchange("hello world topic 2 : " , "a.a.topic1.a.a");
        rabbitProducer.sendToTopicExchange("hello world topic 3 : ", "b.topic2.b");
        rabbitProducer.sendToTopicExchange("hello world topic 4 : " , "b.b.topic2.b.b");//因为匹配规则用的是*， 所以这条收不到
    }

    @Test
    public void sendFanoutMessage(){
        rabbitProducer.sendToFanoutExchange("hello world fanout 1");
        rabbitProducer.sendToFanoutExchange("hello world fanout 2");
    }

    @Test
    public void sendHeadersMessage(){
        rabbitProducer.sendToHeaderExchange("hello world headers 1");
        rabbitProducer.sendToHeaderExchange("hello world headers 2");
    }

}
