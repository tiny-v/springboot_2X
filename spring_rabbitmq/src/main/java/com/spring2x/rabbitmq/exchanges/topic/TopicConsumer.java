package com.spring2x.rabbitmq.exchanges.topic;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author tiny_v
 * @date 2022/4/2.
 */
@Component
public class TopicConsumer {

    public static final Logger logger = LoggerFactory.getLogger(TopicConsumer.class);

    //Topic
    @RabbitListener(queues = "#{topicRabbitElement.Queue_Topic_1}")
    public void topicConsumer1(String content, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) throws IOException {
        logger.info("topic consumer receive the message, channelNum:[{}], tag:[{}], content:[{}], currentTime:[{}]", channel.getChannelNumber(), tag,  content, System.currentTimeMillis());
        try{
            Thread.sleep(500);
            // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息（成功消费，消息从队列中删除 ）
            channel.basicAck(tag, true);
        }catch (Exception e){
            logger.error("=== exception occurs ===:", e);
            // ack返回false
            // requeue-true 并重新回到队列
            channel.basicNack(tag,false,true);
        }
    }

    @RabbitListener(queues = "#{topicRabbitElement.Queue_Topic_2}")
    public void topicConsumer2(String content){
        logger.info("topic consumer 2 receive the message:[{}]", content);
    }

}
