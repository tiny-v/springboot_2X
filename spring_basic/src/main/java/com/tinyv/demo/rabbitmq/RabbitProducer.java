package com.tinyv.demo.rabbitmq;

import com.tinyv.demo.rabbitmq.config.DirectRabbitConfig;
import com.tinyv.demo.rabbitmq.config.TopicRabbitConfig;
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
 * @date 2022/1/7.
 */
@Component
public class RabbitProducer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到Direct Exchange
     * @param messageInfo
     */
    public void sendToDirectExchange(String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(DirectRabbitConfig.Exchange_Direct, bindingKey, message);
    }

    /**
     * 发送消息到Topic Exchange
     * @param messageInfo
     */
    public void sendToTopicExchange(String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.Exchange_Topic, bindingKey, message);
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
