package com.tinyv.demo.rabbitmq.ack;

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
 * @date 2022/1/7.
 */
@Component
public class BatchRabbitConsumer {

    public static final Logger logger = LoggerFactory.getLogger(BatchRabbitConsumer.class);

    /**
     * 注意，此处使用 channel.basicAck的话， 要将配置文件里的自动签收改成manual
     * @param message
     * @param tag
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "#{batchRabbitElement.Batch_Queue_1}")
    public void topicConsumer(String message, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) throws IOException{
        //channel.basicQos(0, 10, false);
        logger.info("batch consumer 1 receive the message, channelNum:[{}], tag:[{}], content:[{}], currentTime:[{}]", channel.getChannelNumber(), tag,  message, System.currentTimeMillis());
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

   /* @RabbitListener(queues = "#{batchRabbitElement.Batch_Queue_1}")
    public void topicConsumer2(String message){
        logger.info("message:[{}]", message);
    }*/



}
