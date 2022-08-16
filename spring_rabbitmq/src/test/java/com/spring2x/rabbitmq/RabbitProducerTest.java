package com.spring2x.rabbitmq;

import com.spring2x.RabbitApps;
import com.spring2x.rabbitmq.exchanges.direct.DirectProducer;
import com.spring2x.rabbitmq.exchanges.direct.DirectRabbitElement;
import com.spring2x.rabbitmq.exchanges.fanout.FanoutProducer;
import com.spring2x.rabbitmq.exchanges.headers.HeadersProducer;
import com.spring2x.rabbitmq.exchanges.topic.TopicProducer;
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
@SpringBootTest(classes={RabbitApps.class})
public class RabbitProducerTest {

    @Autowired
    public DirectProducer directProducer;
    @Autowired
    public FanoutProducer fanoutProducer;
    @Autowired
    public HeadersProducer headersProducer;
    @Autowired
    public TopicProducer topicProducer;

    @Test
    public void sendDirectMessage(){
        for(int i=0 ;i<1000000; i++){
            directProducer.sendToDirectExchange("hello world direct 1 : " + i, DirectRabbitElement.Binding_Key_Direct1);
            //rabbitProducer.sendToDirectExchange("hello world direct 2 : " + i, DirectRabbitElement.Binding_Key_Direct2);
        }
    }

    @Test
    public void sendTopicMessage(){
        topicProducer.sendToTopicExchange("hello world topic 1 : ", "a.topic1.a");
        topicProducer.sendToTopicExchange("hello world topic 2 : " , "a.a.topic1.a.a");
        topicProducer.sendToTopicExchange("hello world topic 3 : ", "b.topic2.b");
        topicProducer.sendToTopicExchange("hello world topic 4 : " , "b.b.topic2.b.b");//因为匹配规则用的是*， 所以这条收不到
    }

    @Test
    public void sendFanoutMessage(){
        fanoutProducer.sendToFanoutExchange("hello world fanout 1");
        fanoutProducer.sendToFanoutExchange("hello world fanout 2");
    }

    @Test
    public void sendHeadersMessage(){
        headersProducer.sendToHeaderExchange("hello world headers 1");
        headersProducer.sendToHeaderExchange("hello world headers 2");
    }

}
