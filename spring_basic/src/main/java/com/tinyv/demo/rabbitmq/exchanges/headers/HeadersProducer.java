package com.tinyv.demo.rabbitmq.exchanges.headers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author tiny_v
 * @date 2022/4/2.
 */
@Component
public class HeadersProducer {

    public static final Logger logger = LoggerFactory.getLogger(HeadersProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到Fanout Exchange
     * @param messageInfo
     */
    public void sendToHeaderExchange(String messageInfo){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageHeaderProperties());
        logger.info("Send Headers Message, Info:[{}]", message);
        rabbitTemplate.convertAndSend(HeadersRabbitElement.Exchange_Headers,null, message);
    }

    /**
     * 设置消息属性
     * @return
     */
    private MessageProperties initMessageHeaderProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setAppId("Spring2X");
        properties.setHeader("Queue", "headers");
        properties.setHeader("type", "any");
        return properties;
    }
}
