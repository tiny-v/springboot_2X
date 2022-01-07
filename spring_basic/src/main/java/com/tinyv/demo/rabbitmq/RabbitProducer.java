package com.tinyv.demo.rabbitmq;

import com.tinyv.demo.rabbitmq.config.RabbitConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

/**
 * @author tiny_v
 * @date 2022/1/7.
 */
@Component
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String messageInfo){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_STOCK, RabbitConfig.ROUTER_STOCK, message);
    }


    /**
     * 设置消息属性
     * @return
     */
    private MessageProperties initMessageProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setAppId("Spring2X");
        return properties;
    }

}
